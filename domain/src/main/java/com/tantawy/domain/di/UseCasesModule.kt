package com.tantawy.domain.di

import com.tantawy.domain.usecases.GetWeatherUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { GetWeatherUseCase(get()) }
}