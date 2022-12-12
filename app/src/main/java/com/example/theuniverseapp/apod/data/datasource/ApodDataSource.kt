package com.example.theuniverseapp.apod.data.datasource

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.provider.Settings.Secure.getString
import androidx.annotation.RequiresApi
import com.example.theuniverseapp.R
import com.example.theuniverseapp.apod.data.ApodService
import com.example.theuniverseapp.apod.data.db.ApodDAO
import com.example.theuniverseapp.apod.data.db.ApodDb
import com.example.theuniverseapp.apod.data.model.ApodDto
import com.example.theuniverseapp.apod.domain.model.ApodModel
import com.example.theuniverseapp.common.db.AppDatabase
import com.example.theuniverseapp.common.utils.getRetrofit
import com.example.theuniverseapp.common.utils.toApodModel
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*
import javax.inject.Inject

interface ApodDataSourceInterface {

    interface Local {

        suspend fun getApodFromDatabase(): ApodModel

        suspend fun insertApodToDatabase(apodDb: ApodDb) {

        }
    }

    interface Remote {
        suspend fun getApodFromApi(): Response<ApodDto>

        suspend fun getApodListFromApi(): Response<List<ApodDto>>
    }
}

//TODO: Inject retrofit  and string resource apikey
class ApodDataSource @Inject constructor(
    private val apodDAO: ApodDAO
) : ApodDataSourceInterface.Local, ApodDataSourceInterface.Remote {

    @SuppressLint("SimpleDateFormat")
    override suspend fun getApodFromDatabase(): ApodModel {

        val current = LocalDate.now().toString()
        return if (apodDAO.isRowIsExist(date = current))
            apodDAO.loadApodId(date = current).toApodModel()
        else
            ApodModel("", "", "", "", "No existe en db", "")
    }

    override suspend fun getApodFromApi(): Response<ApodDto> {

        val retrofit = getRetrofit().create(ApodService::class.java)
        return retrofit.getPictureOfTheDay("apod?api_key=e2B3Gl8ifZIxrBoMzeSdgJJvHxUamnF6MqcB36QM\\")


    }

    @SuppressLint("SimpleDateFormat")
    override suspend fun getApodListFromApi(): Response<List<ApodDto>> {

        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val current = formatter.format(time)

        val period = Period.of(0, 0, 20)
        val date = LocalDate.parse(current)
        val modifiedDate = date.minus(period)

        val retrofit = getRetrofit().create(ApodService::class.java)
        return retrofit.getPictureOfTheDayList("apod?api_key=e2B3Gl8ifZIxrBoMzeSdgJJvHxUamnF6MqcB36QM&start_date=$modifiedDate")
    }

    override suspend fun insertApodToDatabase(apodDb: ApodDb) {
        apodDAO.insertApod(apod = apodDb)
    }

}