package com.example.server

import android.app.Activity
import com.example.data.LoginRequest
import com.example.helpers.FileHelper
import com.example.network.NetworkConnectionManager
import com.google.gson.Gson
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

class MockWebServerManager(private val activity: Activity) {

    companion object {
        const val MOCK_WEB_SERVER_URL = "http://127.0.0.1"
        const val MOCK_WEB_SERVER_PORT = ":9000"
    }

    private var mockWebServer: MockWebServer? = null
    private var isServerStarted = false

    fun start() {
        val networkConnectionManager = NetworkConnectionManager(activity)

        if (!isServerStarted && networkConnectionManager.isConnected()) {

            Thread {
                isServerStarted = true
                mockWebServer = MockWebServer()

                mockWebServer?.start(9000)

                setup()
            }.start()
        }

    }

    fun shutdown() {
        isServerStarted = false
        mockWebServer?.shutdown()
        mockWebServer = null
    }


    private fun setup() {
        mockWebServer?.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
               return when(request.path) {

                   "/api/customers/login" -> {
                        handleLoginRequest(request)
                   }

                   ("/api/directory/search") ->{
                       handleListOfVenuesResponse()
                   }

                   else -> {
                       MockResponse()
                           .setResponseCode(500)
                   }

               }
            }

        }
    }

    private fun handleLoginRequest(request: RecordedRequest) : MockResponse {
        val loginRequest = Gson().fromJson(String(request.body.readByteArray()), LoginRequest::class.java)

        return if (loginRequest.email == "test@testmenu.app" && loginRequest.password == "test1234") {
            MockResponse()
                .setResponseCode(200)
                .setBody(FileHelper.loadJson(activity, "LoginResponseSuccess.json"))
        } else {
            MockResponse()
                .setResponseCode(500)
                .setBody(FileHelper.loadJson(activity, "LoginResponseError.json"))
        }

    }

    private fun handleListOfVenuesResponse(): MockResponse {
        return MockResponse().setResponseCode(200).setBody(MockDataHelper.getListOfVenuesJson())
    }


}