package com.example.theuniverseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.theuniverseapp.apod.data.ApodService
import com.example.theuniverseapp.apod.presentation.ApodViewModel
import com.example.theuniverseapp.apod.utils.getRetrofit
import com.example.theuniverseapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val bottomNavigationView = binding.bottomNavigationView
      //  val navContoller = findNavController(binding.fragmentContainerView.id)

        val retrofit = getRetrofit().create(ApodService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            println(retrofit.getPictureOfTheDay(getString(R.string.api_key)).body())
        }
    }
}