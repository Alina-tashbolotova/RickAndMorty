package com.example.rickandmorty.di

import com.example.data.repositories.ApiRepositoryImpl
import com.example.data.repositories.CharactersRepositoryImpl
import com.example.data.repositories.EpisodesRepositoryImpl
import com.example.data.repositories.LocationsRepositoryImpl
import com.example.domain.repositories.ApiRepository
import com.example.domain.repositories.CharactersRepository
import com.example.domain.repositories.EpisodesRepository
import com.example.domain.repositories.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn((SingletonComponent::class))
abstract class RepositoriesModule {

    @Binds
    abstract fun bindCharacterRepository(
        charactersRepositoryImpl: CharactersRepositoryImpl,
    ): CharactersRepository

    @Binds
    abstract fun bindEpisodeRepository(
        episodeRepositoryImpl: EpisodesRepositoryImpl,
    ): EpisodesRepository

    @Binds
    abstract fun bindLocationRepository(
        locationRepositoryImpl: LocationsRepositoryImpl,
    ): LocationRepository

    @Binds
    abstract fun bindApiRepository(
        allRepoImpl: ApiRepositoryImpl
    ): ApiRepository


}