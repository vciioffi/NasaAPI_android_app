package com.example.theuniverseapp.apod.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface ApodDAO {
    @Query("SELECT * FROM apod")
    suspend fun getAll(): List<ApodDb>

    @Query("SELECT * FROM apod WHERE date = (:date)")
    suspend fun loadApodId(date: String): ApodDb

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(vararg apods: ApodDb)

    @Insert(onConflict = REPLACE)
    suspend fun insertApod(apod: ApodDb)

    @Delete
    suspend fun delete(apodDb: ApodDb)

    @Query("SELECT EXISTS(SELECT * FROM apod WHERE date = (:date))")
    suspend fun isRowIsExist(date : String) : Boolean


}