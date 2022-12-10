package com.example.theuniverseapp.apod.data.di

import android.content.Context
import androidx.room.Room
import com.example.theuniverseapp.apod.data.db.ApodDAO
import com.example.theuniverseapp.common.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Provides
    fun provideApodDao(appDatabase: AppDatabase): ApodDAO {
        return appDatabase.apodDao()
    }

}