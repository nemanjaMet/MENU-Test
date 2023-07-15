package com.example.menutest.constants

class Constants {

    companion object {
        const val PREF_NAME = "app_data"
    }

    object PreferenceName {
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
    }

    object ErrorCode {
        const val INVALID_CREDENTIALS = 100
        const val NO_INTERNET_CONNECTION = 101
    }


}