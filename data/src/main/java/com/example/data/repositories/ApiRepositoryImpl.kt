package com.example.data.repositories

import com.example.data.remote.apiservices.FilterApi
import com.example.data.remote.dtos.toDomain
import com.example.data.repositories.base.BaseRepository
import com.example.domain.repositories.ApiRepository
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val service: FilterApi,
) : BaseRepository(), ApiRepository {


    override fun fetchCharacter(name: String) = doRequest {
        service.fetchCharacters(name).results.map { it.toDomain() }
    }

    override fun fetchEpisodes(name: String) = doRequest {
        service.fetchEpisodes(name).results.map { it.toDomain() }
    }

    override fun fetchLocations(name: String) = doRequest {
        service.fetchLocation(name).results.map { it.toDomain() }
    }
}