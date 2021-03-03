package com.example.androiddevchallenge

import com.squareup.moshi.Json

data class PuppyRandomImageApiModel(
    @field:Json(name = "message")
    val message: String,
    @field:Json(name = "status")
    val status: String
)