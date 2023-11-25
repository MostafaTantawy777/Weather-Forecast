package com.tantawy.data.di

import com.tantawy.data.repositoryimpl.WeatherRepositoryImpl
import com.tantawy.domain.repository.WeatherRepository
import org.koin.dsl.module

val repoModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
}