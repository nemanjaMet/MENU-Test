package com.example.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.controller.DataController
import com.example.network.NetworkApi
import com.example.server.MockWebServerManager
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RetrofitClientInstrumentedTest {

    private val mockWebServer = MockWebServerManager(InstrumentationRegistry.getInstrumentation().context)

    @Before
    fun setup() {
        mockWebServer.start()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testLogin_correctCredentials() {

        val loginRequest = LoginRequest("test@testmenu.app", "test1234")

        val response = runBlocking {
            NetworkApi.retrofitService.executeLogin(loginRequest)
        }

        val isResponseBodyEmptyOrNull = response.body()?.byteStream()?.readBytes()?.isEmpty() ?: true

        assert(response.code() == 200)
        assert(!isResponseBodyEmptyOrNull)
    }

    @Test
    fun testLogin_wrongCredentials() {
        val loginRequest = LoginRequest("test@testmenu.app", "1234test")

        val response = runBlocking {
            NetworkApi.retrofitService.executeLogin(loginRequest)
        }

        val isResponseBodyEmptyOrNull = response.errorBody()?.byteStream()?.readBytes()?.isEmpty() ?: true

        assert(response.code() == 500)
        assert(!isResponseBodyEmptyOrNull)
    }

    @Test
    fun testDataControllerLogin_resultAccessToken() {
        val loginRequest = LoginRequest("test@testmenu.app", "test1234")

        val dataResponse = runBlocking {
            DataController().login(loginRequest)
        }

        assert(dataResponse.status == ResponseStatus.SUCCESS)
        assert(dataResponse.data.isNotEmpty())
    }

    @Test
    fun testDataControllerLogin_wrongCredentials() {
        val loginRequest = LoginRequest("test@testmenu.app", "1234test")

        val dataResponse = runBlocking {
            DataController().login(loginRequest)
        }

        assert(dataResponse.status == ResponseStatus.ERROR)
    }

    @Test
    fun testDataController_GetVenues() {
        val dataResponse = runBlocking {
            DataController().getListOfVenues()
        }

        assert(dataResponse.status == ResponseStatus.SUCCESS)
        assert(dataResponse.data.isNotEmpty())
    }


}