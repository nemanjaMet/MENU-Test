package com.example.menutest.models

enum class SignInStatus(var msg: String = "") {
    IDLE,
    IN_PROGRESS,
    FAILED,
    SUCCESS
}