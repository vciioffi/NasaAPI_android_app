package com.example.theuniverseapp.apod.domain.usecases

import com.example.theuniverseapp.apod.data.repository.ApodRepository
import com.example.theuniverseapp.apod.domain.model.ApodModel
import com.example.theuniverseapp.apod.utils.toApodModel

//TODO: Inject repository
class GetApodUc() {

    val repository: ApodRepository = ApodRepository()

   suspend fun invoke(): ApodModel {
       return repository.getApodResult().toApodModel()
    }
}