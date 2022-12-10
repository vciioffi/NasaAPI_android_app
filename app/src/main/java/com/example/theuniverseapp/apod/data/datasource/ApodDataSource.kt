package com.example.theuniverseapp.apod.data.datasource

import android.content.res.Resources
import android.provider.Settings.Secure.getString
import com.example.theuniverseapp.R
import com.example.theuniverseapp.apod.data.ApodService
import com.example.theuniverseapp.apod.data.db.ApodDAO
import com.example.theuniverseapp.apod.data.model.ApodDto
import com.example.theuniverseapp.common.db.AppDatabase
import com.example.theuniverseapp.common.utils.getRetrofit
import retrofit2.Response
import javax.inject.Inject

interface ApodDataSourceInterface{

    interface Local{

        suspend fun getApodFromDatabase(){

        }

    }
    interface Remote{
        suspend fun getApodFromApi():Response<ApodDto>
    }
}

//TODO: Inject retrofit  and string resource apikey
class ApodDataSource @Inject constructor (
    private val apodDAO: ApodDAO
        ): ApodDataSourceInterface.Local,ApodDataSourceInterface.Remote{
    override suspend fun getApodFromDatabase() {
        TODO("Not yet implemented")
    }
    override suspend fun getApodFromApi():Response<ApodDto> {
        val retrofit = getRetrofit().create(ApodService::class.java)
      //  val apiKey = Resources.getSystem().getString(R.string.api_key)
       return retrofit.getPictureOfTheDay("apod?api_key=e2B3Gl8ifZIxrBoMzeSdgJJvHxUamnF6MqcB36QM")
    }


}