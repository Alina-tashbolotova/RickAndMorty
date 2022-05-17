package com.example.rickandmorty.di

import com.example.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    val retrofitClient = RetrofitClient()

    @Provides
    @Singleton
    fun provideCharacterApiServices() = retrofitClient.provideCharacterApi()

    @Provides
    @Singleton
    fun provideLocationsApiServices() = retrofitClient.provideLocationApi()

    @Provides
    @Singleton
    fun provideEpisodesApiServices() = retrofitClient.provideEpisodeApi()

    @Provides
    @Singleton
    fun provideApiServices() = retrofitClient.provideApi()

}