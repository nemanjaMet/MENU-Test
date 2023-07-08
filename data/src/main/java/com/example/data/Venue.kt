package com.example.data

import com.squareup.moshi.Json

data class Venue(
    @Json(name = "name")
    val name: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "is_open")
    val isOpen: Boolean,
    @Json(name = "Welcome_message")
    val welcomeMessage: String,
    @Json(name = "images")
    val images: Images
)
