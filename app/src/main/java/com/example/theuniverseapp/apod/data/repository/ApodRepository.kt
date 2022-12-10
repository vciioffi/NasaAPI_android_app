package com.example.theuniverseapp.apod.data.repository

import com.example.theuniverseapp.apod.data.datasource.ApodDataSource
import com.example.theuniverseapp.apod.data.datasource.ApodDataSourceInterface
import com.example.theuniverseapp.apod.data.model.ApodDto
import javax.inject.Inject

//TODO: Inject datasource
class ApodRepository @Inject constructor(
    private val apodDataSource : ApodDataSource

) {


    suspend fun getApodResult(): ApodDto {
        //TODO: update methos so it gets info from db
        val response = apodDataSource.getApodFromApi()
        if (response.isSuccessful) {
            val apodDto: ApodDto = response.body()!!
            return apodDto
        }
        else
            //TODO: Call database
            return ApodDto("","","","","","","vacio en el repository","")
    }

}