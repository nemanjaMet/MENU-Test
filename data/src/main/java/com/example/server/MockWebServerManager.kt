package com.example.server

import android.content.Context
import android.util.Log
import com.example.data.LoginRequest
import com.example.helpers.FileHelper
import com.google.gson.Gson
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.json.JSONObject
import kotlin.math.log

class MockWebServerManager(private val context: Context) {

    companion object {
        const val MOCK_WEB_SERVER_URL = "http://127.0.0.1"
        const val MOCK_WEB_SERVER_PORT = ":9000"
    }

    //private val url = MOCK_WEB_SERVER_URL

  /*  fun getBaseUrl(): String {
        return url
    }*/

    private var mockWebServer: MockWebServer? = null
    private var isServerStarted = false

    fun start() {

        if (!isServerStarted) {

            Thread {
                isServerStarted = true
                mockWebServer = MockWebServer()

                //Log.d("MockWebServerManager", "START")

                mockWebServer?.start(9000)

/*
                val hname = mockWebServer?.hostName
                //val url = mockWebServer?.url(url)
                Log.d("MockWebServerManager", "URL: $url, hostName: ${hname}")
*/

                setup()


            }.start()
        } else {
            Log.d("MockWebServerManager", "SERVER ALREADY STARTED")
        }

    }

    fun shutdown() {

        isServerStarted = false
        mockWebServer?.shutdown()
        mockWebServer = null

        Log.d("MockWebServerManager", "SHUTDOWN")
    }


    private fun setup() {


        mockWebServer?.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
               return when(request.path) {

                   "/api/customers/login" -> {
                        handleLoginRequest(request)
                   }

                   ("/success") ->{
                       MockResponse()
                           .setResponseCode(200)
                           .setBody("success")
                   }
                   ("/error") ->{
                       MockResponse()
                           .setResponseCode(500)
                           .setBody("error")
                   }
                   else -> {
                       MockResponse()
                           .setResponseCode(200)
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
                .setBody(FileHelper.loadJson(context, "LoginResponseSuccess.json"))
        } else {
            MockResponse()
                .setResponseCode(500)
                .setBody(FileHelper.loadJson(context, "LoginResponseError.json"))
        }

    }

}