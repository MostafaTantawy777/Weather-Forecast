package com.tantawy.weatherforecast.presentation.ui

import android.os.Bundle
import android.os.Build
import com.tantawy.weatherforecast.base.activity.BaseActivity
import com.tantawy.weatherforecast.databinding.ActivityMainBinding
import com.tantawy.weatherforecast.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.annotation.RequiresApi
import com.tantawy.domain.model.request.WeatherRequest
import com.tantawy.weatherforecast.R
import com.tantawy.weatherforecast.presentation.viewmodel.TemperatureUnit
import com.tantawy.weatherforecast.presentation.viewmodel.WeatherStatus
import com.tantawy.weatherforecast.utils.WeatherLocationManager
import kotlin.math.roundToInt

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModel()
    private val weatherLocationManager = WeatherLocationManager(this@MainActivity)

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.weather = weatherViewModel
        fetchWeatherData()
        updateTemperatureDisplay(TemperatureUnit.CELSIUS)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun fetchWeatherData() {
        weatherLocationManager.getLocation { addresses ->
            weatherViewModel.fetchWeather(
                WeatherRequest(
                    addresses!![0].latitude,
                    addresses[0].longitude
                )
            )
            binding.tvCity.text=addresses[0].locality
        }
    }

    private fun updateTemperatureDisplay(unit: TemperatureUnit) {
        weatherViewModel.apply {
            weather.observe(this@MainActivity) {
                it?.let {
                   var temperatureInKelvin = it[0]?.main?.temp?:0.0
                    setWeatherStatusIcon(it[0]?.weather!![0].main.toString())
                    val convertedTemperature = when (unit) {
                        TemperatureUnit.CELSIUS -> convertKelvinToCelsius(temperatureInKelvin)
                        TemperatureUnit.FAHRENHEIT -> convertKelvinToFahrenheit(temperatureInKelvin)
                        TemperatureUnit.KELVIN -> temperatureInKelvin
                    }
                    binding.tvTemp.text = "${convertedTemperature.roundToInt()} ${unit.symbol}"
                }
            }
        }
    }

    private fun setWeatherStatusIcon(status: String) {
        val weatherStatusIcon = when (status) {
            WeatherStatus.Clear.name -> R.drawable.ic_cloudy
            WeatherStatus.Cloudy.name -> R.drawable.ic_cloudy
            WeatherStatus.Rainy.name -> R.drawable.ic_rainy
            WeatherStatus.Sunny.name -> R.drawable.ic_sunny
            else -> {R.drawable.ic_sunny}
        }
        binding.weatherIcon.setImageResource(weatherStatusIcon)
    }
}