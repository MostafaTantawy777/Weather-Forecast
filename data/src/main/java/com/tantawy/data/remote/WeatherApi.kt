package com.tantawy.data.remote

import com.tantawy.data.remote.RemoteConstants.API_KEY
import com.tantawy.domain.model.MultiBaseResponse
import com.tantawy.domain.model.WeatherList
import retrofit2.Response
import retrofit2.http.*

interface WeatherApi {

//    @GET("forecast?lat=30.2168012&lon=31.3513601&appid=$API_KEY")
//    suspend fun getWeather(): Response<MultiBaseResponse<WeatherList>>

    @GET("forecast")
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
    ): Response<MultiBaseResponse<WeatherList>>
}