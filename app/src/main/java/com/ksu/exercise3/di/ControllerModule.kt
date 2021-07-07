package com.ksu.exercise3.di

import com.ksu.exercise3.BuildConfig
import com.ksu.exercise3.data.endpoints.NewsEndpoint
import com.ksu.exercise3.network.ApiKeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val controllerModule = module {
    factory { ApiKeyInterceptor() }
    factory { buildOkHttpClient(get()) }
    factory { buildRetrofitClient(get())}
    single { news(get()) }
}

private fun buildRetrofitClient(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}

private fun buildOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient {
    val networkLogInterceptor = HttpLoggingInterceptor()
    networkLogInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(networkLogInterceptor)
            .connectTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .build()
}

private fun news(retrofit: Retrofit): NewsEndpoint = retrofit.create(NewsEndpoint::class.java)

