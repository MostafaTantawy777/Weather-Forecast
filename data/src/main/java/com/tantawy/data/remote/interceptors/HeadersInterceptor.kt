package com.tantawy.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.koin.core.component.KoinComponent

class HeadersInterceptor() : Interceptor, KoinComponent {

    private val keyAuthorization = "Authorization"
    private val keyDeviceType = "Device-Type"
    private val appVersion = "App-Version"
    private val keyLanguage = "Accept-Language"

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(createNewRequestWithApiKey(chain.request()))

    private fun createNewRequestWithApiKey(request: Request): Request {
        val token = "dummy token"
        val requestBuilder = request.newBuilder()
            .addHeader(keyLanguage, "en")
            .addHeader(keyAuthorization, "token $token")
            .addHeader(keyDeviceType, "android")
            .addHeader(appVersion, "1.0.0.0.0")

        return requestBuilder.build()
    }
}