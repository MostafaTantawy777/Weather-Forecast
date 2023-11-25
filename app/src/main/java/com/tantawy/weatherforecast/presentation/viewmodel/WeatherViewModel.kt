package com.tantawy.weatherforecast.presentation.viewmodel

import androidx.lifecycle.*
import com.tantawy.domain.model.WeatherList
import com.tantawy.domain.usecases.GetWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _weather = MutableLiveData<List<WeatherList?>>()
    val weather: LiveData<List<WeatherList?>> = _weather

    init {
        fetchWeather()
    }

    private fun fetchWeather() {
        viewModelScope.launch(context = Dispatchers.Main) {
            weatherUseCase.execute()
                .catch {
                    it.printStackTrace()
                }
                .onCompletion { }
                .collect {
                    _weather.value = it?.body()?.result
                }
        }
    }
}