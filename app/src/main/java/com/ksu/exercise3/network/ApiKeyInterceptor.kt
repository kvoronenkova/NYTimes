package com.ksu.exercise3.network

import com.ksu.exercise3.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiKeyInterceptor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestWithoutApiKey = chain.request()
        val url = requestWithoutApiKey.url()
                .newBuilder()
                .addQueryParameter(PARAM_API_KEY, BuildConfig.API_KEY)
                .build()
        val requestWithAttachedApiKey = requestWithoutApiKey.newBuilder()
                .url(url)
                .build()
        return chain.proceed(requestWithAttachedApiKey)
    }

    companion object {
        private const val PARAM_API_KEY = "api-key"
    }
}