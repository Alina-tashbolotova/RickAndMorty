package com.example.data.repositories

import com.example.data.remote.apiservices.EpisodesApi
import com.example.data.remote.dtos.toDomain
import com.example.data.repositories.base.BaseRepository
import com.example.domain.repositories.EpisodesRepository
import javax.inject.Inject


class EpisodesRepositoryImpl @Inject constructor(private val service: EpisodesApi) :
    BaseRepository(),
    EpisodesRepository {

    override fun fetchEpisodes(page: Int) = doRequest {
        service.fetchEpisodes(page).results.map { it.toDomain() }
    }

    override fun fetchEpisode(id: Int) = doRequest {
        service.fetchEpisode(id).toDomain()
    }


}