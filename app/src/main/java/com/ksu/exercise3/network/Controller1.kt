package com.ksu.exercise3.network

import com.ksu.exercise3.endpoints.NewsEndpoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Controller private constructor() {
    private val URL = "https://api.nytimes.com/svc/mostpopular/v2/viewed/"
    private val API_KEY = "dfoqo2KLBJ3PmQHG1HWoidvru0BablNq"
    private val newsEndpoint: NewsEndpoint
    private fun buildRetrofitClient(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    private fun buildOkHttpClient(): OkHttpClient {
        val networkLogInterceptor = HttpLoggingInterceptor()
        networkLogInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor.Companion.create(API_KEY))
                .addInterceptor(networkLogInterceptor)
                .connectTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
                .build()
    }

    fun news(): NewsEndpoint {
        return newsEndpoint
    }

    companion object {
        private const val TIMEOUT_IN_SECONDS = 2
        private var controller: Controller? = null

        @get:Synchronized
        val instance: Controller?
            get() {
                if (controller == null) controller = Controller()
                return controller
            }
    }

    init {
        val okHttpClient = buildOkHttpClient()
        val retrofit = buildRetrofitClient(okHttpClient)
        newsEndpoint = retrofit.create(NewsEndpoint::class.java)
    }
}