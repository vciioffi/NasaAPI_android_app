package com.example.theuniverseapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.theuniverseapp.apod.data.ApodService
import com.example.theuniverseapp.apod.presentation.ApodViewModel
import com.example.theuniverseapp.common.utils.getRetrofit
import com.example.theuniverseapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        //val bottomNavigationView = binding.bottomNavigationView
      //  val navContoller = findNavController(binding.fragmentContainerView.id)

        val retrofit = getRetrofit().create(ApodService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            var ki = println(retrofit.getPictureOfTheDayList(getString(R.string.api_key )+"&start_date=2022-11-19").body())
        }

    }
}