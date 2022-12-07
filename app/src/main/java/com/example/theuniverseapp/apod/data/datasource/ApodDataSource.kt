package com.example.theuniverseapp.apod.data.datasource

interface ApodDataSourceInterface{

    interface Local{

        suspend fun getApodFromDatabase(){

        }

    }
    interface Remote{
        suspend fun getApodFromApi(){

        }
    }
}

class ApodDataSource(): ApodDataSourceInterface.Local,ApodDataSourceInterface.Remote{
    override suspend fun getApodFromDatabase() {
        TODO("Not yet implemented")
    }
    override suspend fun getApodFromApi() {
        TODO("Not yet implemented")
    }


}