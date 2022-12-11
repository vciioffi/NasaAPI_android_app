package com.example.theuniverseapp.common.di

import android.content.Context
import androidx.room.Room
import com.example.theuniverseapp.common.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "TheUniverseAppDatabase"
        )
            .build()
    }
}