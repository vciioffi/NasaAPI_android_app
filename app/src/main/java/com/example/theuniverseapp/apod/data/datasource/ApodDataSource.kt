package com.example.theuniverseapp.apod.data.datasource

import android.content.res.Resources
import android.provider.Settings.Secure.getString
import com.example.theuniverseapp.R
import com.example.theuniverseapp.apod.data.ApodService
import com.example.theuniverseapp.apod.data.model.ApodDto
import com.example.theuniverseapp.apod.utils.getRetrofit
import retrofit2.Response

interface ApodDataSourceInterface{

    interface Local{

        suspend fun getApodFromDatabase(){

        }

    }
    interface Remote{
        suspend fun getApodFromApi():Response<ApodDto>
    }
}

//TODO: Inject retrofit and db and string resource apikey
class ApodDataSource(): ApodDataSourceInterface.Local,ApodDataSourceInterface.Remote{
    override suspend fun getApodFromDatabase() {
        TODO("Not yet implemented")
    }
    override suspend fun getApodFromApi():Response<ApodDto> {
        val retrofit = getRetrofit().create(ApodService::class.java)
      //  val apiKey = Resources.getSystem().getString(R.string.api_key)
       return retrofit.getPictureOfTheDay("apod?api_key=e2B3Gl8ifZIxrBoMzeSdgJJvHxUamnF6MqcB36QM")
    }


}