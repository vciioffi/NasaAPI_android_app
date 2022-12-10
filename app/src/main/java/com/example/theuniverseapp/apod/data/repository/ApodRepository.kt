package com.example.theuniverseapp.apod.data.repository

import com.example.theuniverseapp.apod.data.datasource.ApodDataSource
import com.example.theuniverseapp.apod.data.datasource.ApodDataSourceInterface
import com.example.theuniverseapp.apod.data.model.ApodDto
import com.example.theuniverseapp.apod.domain.model.ApodModel
import com.example.theuniverseapp.common.utils.toApodDb
import com.example.theuniverseapp.common.utils.toApodModel
import javax.inject.Inject

//TODO: Inject datasource
class ApodRepository @Inject constructor(
    private val apodDataSource : ApodDataSource

) {


    suspend fun getApodResult(): ApodModel {
        val response = apodDataSource.getApodFromApi()
        if (response.isSuccessful) {
            val apodDto: ApodDto = response.body()!!
            apodDto.toApodDb()
            apodDataSource.insertApodToDatabase(apodDto.toApodDb())
            return apodDto.toApodModel()
        }
        else
            return apodDataSource.getApodFromDatabase()
    }

}