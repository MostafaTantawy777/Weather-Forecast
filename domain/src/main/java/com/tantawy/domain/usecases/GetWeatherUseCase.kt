package com.tantawy.domain.usecases

import com.tantawy.domain.model.MultiBaseResponse
import com.tantawy.domain.model.WeatherList
import com.tantawy.domain.model.request.WeatherRequest
import com.tantawy.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) :
    UseCase<WeatherRequest, Flow<Response<MultiBaseResponse<WeatherList>>>> {

    override suspend fun execute(param: WeatherRequest?): Flow<Response<MultiBaseResponse<WeatherList>>> {
        return fetchWeatherList(param as WeatherRequest)
    }

    private suspend fun fetchWeatherList(weatherRequest: WeatherRequest): Flow<Response<MultiBaseResponse<WeatherList>>> {
        return weatherRepository.fetchWeatherList(weatherRequest)
    }
}