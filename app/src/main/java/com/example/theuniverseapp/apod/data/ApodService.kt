package com.example.theuniverseapp.apod.data

import android.provider.Settings.Secure.getString
import com.example.theuniverseapp.R
import com.example.theuniverseapp.apod.data.model.ApodDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApodService {

    @GET
    suspend fun getPictureOfTheDay(@Url url:String):Response<ApodDto>

    @GET
    suspend fun getPictureOfTheDayWithDate(@Url url:String):Response<ApodDto>

    @GET
    suspend fun getPictureOfTheDayList(@Url url: String):Response<List<ApodDto>>

    @GET
    suspend fun getPictureOfTheDayListWithDates(@Url url: String):Response<List<ApodDto>>
}