package com.example.data

data class Venue(
    val title: String,
    val distance: String,
    val location: String,
    val workingTime: String,
    val isWorking: Boolean,
    val welcomeMessage: String,
    val description: String,
    val images: Images
)

/*data class Venue(
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
)*/
