package com.example.theuniverseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.theuniverseapp.apod.data.ApodService
import com.example.theuniverseapp.apod.utils.getRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = getRetrofit().create(ApodService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
           println(retrofit.getPictureOfTheDay(getString(R.string.api_key)).body())
        }
    }
}