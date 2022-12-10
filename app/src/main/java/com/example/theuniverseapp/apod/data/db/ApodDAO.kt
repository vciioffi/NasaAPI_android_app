package com.example.theuniverseapp.apod.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ApodDAO {
    @Query("SELECT * FROM apod")
    fun getAll(): List<ApodDb>

    @Query("SELECT * FROM apod WHERE date = (:date)")
    fun loadApodId(date: String): List<ApodDb>

    @Insert
    fun insertAll(vararg apods: ApodDb)

    @Delete
    fun delete(apodDb: ApodDb)

}