package com.example.theuniverseapp.apod.data.datasource

import android.annotation.SuppressLint
import com.example.theuniverseapp.apod.data.ApodService
import com.example.theuniverseapp.apod.data.db.ApodDAO
import com.example.theuniverseapp.apod.data.db.ApodDb
import com.example.theuniverseapp.apod.data.model.ApodDto
import com.example.theuniverseapp.apod.domain.model.ApodModel
import com.example.theuniverseapp.common.utils.getRetrofit
import com.example.theuniverseapp.common.utils.toApodModel
import retrofit2.Response
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

        suspend fun getApodLisFromApiWithDates(dateStart:Int, dateEnd: Int): Response<List<ApodDto>>
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

    override suspend fun getApodListFromApi(): Response<List<ApodDto>> {

        val current = LocalDate.now().toString()

        val period = Period.of(0, 0, 10)
        val date = LocalDate.parse(current)
        val modifiedDate = date.minus(period)

        val retrofit = getRetrofit().create(ApodService::class.java)
        return retrofit.getPictureOfTheDayList("apod?api_key=e2B3Gl8ifZIxrBoMzeSdgJJvHxUamnF6MqcB36QM&start_date=$modifiedDate")
    }

    override suspend fun getApodLisFromApiWithDates(
        dateStart: Int,
        dateEnd: Int
    ): Response<List<ApodDto>> {
        val current = LocalDate.now().toString()

        val period = Period.of(0, 0, dateEnd-1)
        val date = LocalDate.parse(current)
        val modifiedDateEnd = date.minus(period)

        val periodStart = Period.of(0, 0, dateStart)
        val dateStart = LocalDate.parse(current)
        val modifiedDateStart = dateStart.minus(periodStart)

        val retrofit = getRetrofit().create(ApodService::class.java)
        return retrofit.getPictureOfTheDayList("apod?api_key=e2B3Gl8ifZIxrBoMzeSdgJJvHxUamnF6MqcB36QM&start_date=$modifiedDateStart&end_date=$modifiedDateEnd")

    }

    override suspend fun insertApodToDatabase(apodDb: ApodDb) {
        apodDAO.insertApod(apod = apodDb)
    }

}