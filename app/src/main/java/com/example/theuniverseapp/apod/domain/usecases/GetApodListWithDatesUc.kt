package com.example.theuniverseapp.apod.domain.usecases

import com.example.theuniverseapp.apod.data.repository.ApodRepository
import com.example.theuniverseapp.apod.domain.model.ApodModel
import javax.inject.Inject

class GetApodListWithDatesUc @Inject constructor(
    private val repository: ApodRepository

) {

    suspend fun invoke(start:Int, end: Int): List<ApodModel> {
        return repository.getApodListResultWithDates(start, end)
    }
}