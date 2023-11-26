package com.tantawy.domain.repository

import com.tantawy.domain.model.MultiBaseResponse
import com.tantawy.domain.model.WeatherList
import com.tantawy.domain.model.request.WeatherRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface WeatherRepository {
    suspend fun fetchWeatherList(weatherRequest: WeatherRequest): Flow<Response<MultiBaseResponse<WeatherList>>>
}