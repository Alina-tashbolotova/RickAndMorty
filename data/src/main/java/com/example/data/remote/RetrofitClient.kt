package com.example.data.remote

import com.example.common.constants.Constants
import com.example.data.remote.apiservices.FilterApi
import com.example.data.remote.apiservices.CharactersApi
import com.example.data.remote.apiservices.EpisodesApi
import com.example.data.remote.apiservices.LocationsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    private val okHttpClient: OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(provideLoggingInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val provideRetrofitClient = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun provideCharacterApi(): CharactersApi = provideRetrofitClient.create(
        CharactersApi::class.java
    )

    fun provideLocationApi(): LocationsApi = provideRetrofitClient.create(
        LocationsApi::class.java
    )

    fun provideEpisodeApi(): EpisodesApi = provideRetrofitClient.create(
        EpisodesApi::class.java
    )

    fun provideApi(): FilterApi = provideRetrofitClient.create(
        FilterApi::class.java
    )
}
