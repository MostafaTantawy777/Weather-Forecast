package com.tantawy.domain.usecases

interface UseCase<in P, R> {
    suspend  fun execute(param: P? = null): R
}