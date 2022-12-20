package com.example.theuniverseapp.apod.domain.usecases

import com.example.theuniverseapp.apod.data.repository.ApodRepository
import com.example.theuniverseapp.apod.domain.model.ApodModel
import com.example.theuniverseapp.common.utils.toApodModel
import javax.inject.Inject


class GetApodUc @Inject constructor(
    val repository: ApodRepository

) {


    suspend operator fun invoke(): ApodModel {
        return repository.getApodResult()
    }
}