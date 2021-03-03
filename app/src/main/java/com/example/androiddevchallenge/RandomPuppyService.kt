package com.example.androiddevchallenge

import retrofit2.http.GET

interface RandomPuppyService {

    @GET("/api/breeds/image/random")
    suspend fun getRandom(): PuppyRandomImageApiModel
}