package com.example.menutest.models

enum class SignInStatus(var msg: String = "", var errorCode: Int = -1) {
    IDLE,
    IN_PROGRESS,
    FAILED,
    SUCCESS
}