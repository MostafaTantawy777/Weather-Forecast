package com.tantawy.domain.usecases

import com.tantawy.domain.model.MultiBaseResponse
import com.tantawy.domain.model.WeatherList
import com.tantawy.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) :
    UseCase<Boolean, Flow<Response<MultiBaseResponse<WeatherList>>>> {

    override suspend fun execute(fromCash: Boolean?): Flow<Response<MultiBaseResponse<WeatherList>>> {
        return fetchWeatherList()
    }

    private suspend fun fetchWeatherList(): Flow<Response<MultiBaseResponse<WeatherList>>> {
        return weatherRepository.fetchWeatherList()
    }
}