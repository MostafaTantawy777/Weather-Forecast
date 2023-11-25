package com.tantawy.weatherforecast.presentation.ui

import android.os.Bundle
import android.os.Build
import com.tantawy.weatherforecast.base.activity.BaseActivity
import com.tantawy.weatherforecast.databinding.ActivityMainBinding
import com.tantawy.weatherforecast.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.annotation.RequiresApi
import com.tantawy.weatherforecast.utils.WeatherLocationManager

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModel()
    private val weatherLocationManager = WeatherLocationManager(this@MainActivity)

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding?.lifecycleOwner = this
        binding?.weather = weatherViewModel
        weatherLocationManager.getLocation { addresses ->
            binding.tvDesc.text = "Latitude\n${addresses!![0].latitude}+" +
                    "Longitude\n" +
                    "${addresses[0].longitude}" + "Address\n" +
                    "${addresses[0].getAddressLine(0)}"
        }
    }
}