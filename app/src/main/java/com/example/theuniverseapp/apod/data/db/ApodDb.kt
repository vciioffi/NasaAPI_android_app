package com.example.theuniverseapp.apod.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apod")
data class ApodDb(
    @PrimaryKey val date: String,
    val copyright: String?,
    val explanation: String,
    val hdurl: String,
    val title: String,
    val url: String
)
