package com.example.network

import android.app.Activity
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(activity: Activity) : Interceptor {

    private val networkConnectionManager = NetworkConnectionManager(activity)

    override fun intercept(chain: Interceptor.Chain): Response {

        if (!networkConnectionManager.isConnected())
            throw NoConnectivityException()

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())

    }


}