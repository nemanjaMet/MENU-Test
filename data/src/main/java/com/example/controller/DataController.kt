package com.example.controller

import android.util.Log
import com.example.data.DataResponse
import com.example.data.LoginRequest
import com.example.data.ResponseStatus
import com.example.network.NetworkApi
import org.json.JSONObject

class DataController {

    suspend fun login(loginRequest: LoginRequest): DataResponse {

        try {
            val response = NetworkApi.retrofitService.executeLogin(loginRequest)

            val responseCode = response.code()

            val responseJson = String(response.body()!!.byteStream().readBytes())
            val bodyResponseObject = JSONObject(responseJson)

            return if (responseCode == 200) {
                val accessToken = bodyResponseObject.getString("access_token")

                DataResponse(status = ResponseStatus.SUCCESS, data = accessToken)
            } else {
                val error = bodyResponseObject.getString("error")
                val message = bodyResponseObject.getString("message")

                DataResponse(status = ResponseStatus.ERROR, error = error, errorDetails = message)
            }

        } catch (ex: Exception) {
            Log.e("DataController", ex.message, ex)
            return DataResponse(status = ResponseStatus.ERROR)
        }


    }

}