package com.tantawy.weatherforecast.presentation.viewmodel

import androidx.lifecycle.*
import com.tantawy.domain.model.WeatherList
import com.tantawy.domain.model.request.WeatherRequest
import com.tantawy.domain.usecases.GetWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _weather = MutableStateFlow<List<WeatherList?>>(emptyList())
    val weather: StateFlow<List<WeatherList?>> = _weather

    private val error = MutableLiveData<String>()

     val city = MutableStateFlow<String>("")

    fun fetchWeather(weatherRequest: WeatherRequest) {
        viewModelScope.launch(context = Dispatchers.Main) {
            try {
                weatherUseCase.execute(weatherRequest)
                    .catch {
                        it.printStackTrace()
                    }
                    .onCompletion { }
                    .collect {
                        _weather.value = it?.body()?.result!!
                    }
            }catch (e:Exception){
                error.value=e.message
            }

        }
    }

    fun convertKelvinToCelsius(kelvin: Double): Double {
        return kelvin - 273.15
    }

    fun convertCelsiusToFahrenheit(celsius: Double): Double {
        return celsius * 9 / 5 + 32
    }

    fun convertKelvinToFahrenheit(kelvin: Double): Double {
        return convertCelsiusToFahrenheit(convertKelvinToCelsius(kelvin))
    }
}

enum class TemperatureUnit(val symbol: String) {
    CELSIUS("°C"),
    FAHRENHEIT("°F"),
    KELVIN("°K")
}

enum class WeatherStatus {
    Clear,
    Clouds,
    Rain,
    Sunny
}