package com.example.controller

import android.app.Activity
import android.util.Log
import com.example.data.DataResponse
import com.example.data.LoginRequest
import com.example.data.ResponseStatus
import com.example.data.VenueTest
import com.example.network.NetworkApi
import org.json.JSONObject
import java.net.ConnectException

class DataController {

    fun createRetrofitWithInterceptor(activity: Activity) {
        NetworkApi.createRetrofitWithInterceptor(activity)
    }

    companion object {
        private const val LOG_TAG = "DataController"
    }

    suspend fun login(loginRequest: LoginRequest): DataResponse {

        try {
            val response = NetworkApi.retrofitService.executeLogin(loginRequest)

            val responseCode = response.code()

            return if (responseCode == 200) {
                val responseJson = String(response.body()!!.byteStream().readBytes())
                val bodyResponseObject = JSONObject(responseJson)

                val accessToken = bodyResponseObject.getString("access_token")

                DataResponse(status = ResponseStatus.SUCCESS, data = accessToken)
            } else {
                val responseJson = String(response.errorBody()!!.byteStream().readBytes())
                val bodyResponseObject = JSONObject(responseJson)

                val error = bodyResponseObject.getString("error")
                val message = bodyResponseObject.getString("message")

                DataResponse(status = ResponseStatus.ERROR, error = error, errorDetails = message)
            }

        } catch (ex: Exception) {
            Log.e(LOG_TAG, ex.message, ex)
            return DataResponse(status = ResponseStatus.ERROR)
        }


    }

    suspend fun getListOfVenues(): DataResponse  { // ArrayList<VenueTest>?

        try {

            val response = NetworkApi.retrofitService.getVenues()

            //return ArrayList(listOfVenues)
            return DataResponse(ResponseStatus.SUCCESS, data = String(response.body()!!.byteStream().readBytes()))
        }
        catch (ex: ConnectException) {
            Log.e(LOG_TAG, ex.message, ex)

            return DataResponse(ResponseStatus.CONNECTION_ERROR)
        }
        catch (ex: Exception) {
            Log.e(LOG_TAG, ex.message, ex)

            return DataResponse(ResponseStatus.UNKNOWN_ERROR)
        }

    }

}