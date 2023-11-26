package com.tantawy.domain.model.request

data class WeatherRequest(
    val lat: Double,
    val long: Double,
    val appId: String? = null,
)