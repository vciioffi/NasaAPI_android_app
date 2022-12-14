package com.example.theuniverseapp.apod.domain.usecases

import com.example.theuniverseapp.apod.data.repository.ApodRepository
import com.example.theuniverseapp.apod.domain.model.ApodModel
import javax.inject.Inject

class GetApodWithDateUc @Inject constructor(
    private val repository: ApodRepository

) {

    suspend fun invoke(date:String): ApodModel {
        return repository.getApodWithDate(date)
    }
}