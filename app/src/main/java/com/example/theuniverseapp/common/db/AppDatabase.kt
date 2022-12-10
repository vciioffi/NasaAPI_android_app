package com.example.theuniverseapp.common.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.theuniverseapp.apod.data.db.ApodDAO
import com.example.theuniverseapp.apod.data.db.ApodDb

@Database(entities = [ApodDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun apodDao(): ApodDAO
}