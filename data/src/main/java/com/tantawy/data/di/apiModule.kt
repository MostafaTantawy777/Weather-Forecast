package com.tantawy.data.di

import com.tantawy.data.remote.WeatherApi
import org.koin.dsl.module
import retrofit2.Retrofit

val  apiModule = module {
    factory { get<Retrofit>().create(WeatherApi::class.java)}
}