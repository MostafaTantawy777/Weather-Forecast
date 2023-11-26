package com.tantawy.data.repositoryimpl

import android.util.Log
import com.tantawy.data.remote.RemoteConstants.API_KEY
import com.tantawy.data.remote.WeatherApi
import com.tantawy.domain.model.MultiBaseResponse
import com.tantawy.domain.model.WeatherList
import com.tantawy.domain.model.request.WeatherRequest
import com.tantawy.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class WeatherRepositoryImpl(private val weatherApi: WeatherApi) : WeatherRepository {

    override suspend fun fetchWeatherList(weatherRequest: WeatherRequest): Flow<Response<MultiBaseResponse<WeatherList>>> {
        val weatherData = weatherApi.getWeather(weatherRequest.lat,weatherRequest.long,API_KEY)
        return flow {
            emit(weatherData)
        }
    }
}