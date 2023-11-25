package com.tantawy.data.remote

import com.tantawy.domain.model.MultiBaseResponse
import com.tantawy.domain.model.WeatherList
import retrofit2.Response
import retrofit2.http.*

interface WeatherApi {

    @GET("forecast?lat=30.2168012&lon=31.3513601&appid=63fd90c4d3102064442f7a4a21a0c5c9")
    suspend fun getWeather(): Response<MultiBaseResponse<WeatherList>>
}