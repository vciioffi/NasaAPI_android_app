package com.example.theuniverseapp.apod.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val url: String = "https://api.nasa.gov/planetary/"

 fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}