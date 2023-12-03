package com.tantawy.weatherforecast.presentation.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.tantawy.domain.model.request.WeatherRequest
import com.tantawy.weatherforecast.presentation.ui.ui.theme.WeatherForecastTheme
import com.tantawy.weatherforecast.presentation.viewmodel.WeatherViewModel
import com.tantawy.weatherforecast.utils.WeatherLocationManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import weatherScreen

class WeatherActivity : ComponentActivity() {

    private val weatherViewModel: WeatherViewModel by viewModel()
    private val weatherLocationManager = WeatherLocationManager(this@WeatherActivity)
    private var cityName: String? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchWeatherData()
        setContent {
            WeatherForecastTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    weatherScreen(Modifier, weatherViewModel,cityName.toString())
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun fetchWeatherData() {
        weatherLocationManager.getLocation { addresses ->
            weatherViewModel.fetchWeather(
                WeatherRequest(
                    addresses?.get(0)?.latitude ?: 0.0,
                    addresses?.get(0)?.longitude ?: 0.0
                )
            )
            weatherViewModel.city.value =addresses?.get(0)?.locality ?: ""
            cityName = "${addresses?.get(0)?.latitude}"
        }
    }
}