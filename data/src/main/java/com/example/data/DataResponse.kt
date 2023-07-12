package com.example.data

data class DataResponse (
        val status: ResponseStatus,
        val error: String? = null,
        val errorDetails: String? = null,
        val data: String = ""
)