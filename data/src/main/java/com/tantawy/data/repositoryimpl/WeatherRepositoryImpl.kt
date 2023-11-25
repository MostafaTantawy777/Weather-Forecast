package com.tantawy.data.repositoryimpl

import com.tantawy.data.remote.WeatherApi
import com.tantawy.domain.model.MultiBaseResponse
import com.tantawy.domain.model.WeatherList
import com.tantawy.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class WeatherRepositoryImpl(private val weatherApi: WeatherApi) : WeatherRepository {

    override suspend fun fetchWeatherList(): Flow<Response<MultiBaseResponse<WeatherList>>> {
        val weatherData = weatherApi.getWeather()
        return flow {
            emit(weatherData)
        }
    }
}