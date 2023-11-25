package com.tantawy.weatherforecast.presentation.ui

import android.os.Bundle
import com.tantawy.weatherforecast.base.activity.BaseActivity
import com.tantawy.weatherforecast.databinding.ActivityMainBinding
import com.tantawy.weatherforecast.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding?.lifecycleOwner = this
        binding?.weather = weatherViewModel
    }
}