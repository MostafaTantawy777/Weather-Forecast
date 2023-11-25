package com.tantawy.domain.repository

import com.tantawy.domain.model.MultiBaseResponse
import com.tantawy.domain.model.WeatherList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface WeatherRepository {
    suspend fun fetchWeatherList(): Flow<Response<MultiBaseResponse<WeatherList>>>
}