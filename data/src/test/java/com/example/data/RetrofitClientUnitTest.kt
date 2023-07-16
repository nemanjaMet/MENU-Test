package com.example.data

import com.example.controller.DataController
import com.example.network.NetworkApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RetrofitClientUnitTest {

    @Test
    fun testRetrofitInstance() {
        val instance = NetworkApi.getRetrofit()
        assert(instance.baseUrl().toUrl().toString() == NetworkApi.getBaseUrl())
    }

    @Test
    fun testLogin_noInternetConnection() {
        val loginRequest = LoginRequest("test@testmenu.app", "test1234")

        val dataResponse = runBlocking {
            DataController().login(loginRequest)
        }

        assert(dataResponse.status == ResponseStatus.CONNECTION_ERROR)
    }

    @Test
    fun testGetVenues_noInternetConnection() {

        val dataResponse = runBlocking {
            DataController().getListOfVenues()
        }

        assert(dataResponse.status == ResponseStatus.CONNECTION_ERROR)
    }


}