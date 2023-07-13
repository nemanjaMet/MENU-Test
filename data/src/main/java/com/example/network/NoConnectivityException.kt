package com.example.network

import okio.IOException


class NoConnectivityException: IOException() {

    override fun getLocalizedMessage(): String {
        return "No Internet Connection"
    }

}