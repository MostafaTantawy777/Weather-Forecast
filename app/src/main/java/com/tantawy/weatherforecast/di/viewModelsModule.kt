package com.tantawy.weatherforecast.di

import com.tantawy.weatherforecast.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val  viewModelsModule = module {
    viewModel { WeatherViewModel(get()) }
}