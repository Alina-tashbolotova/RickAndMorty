package com.example.domain.repositories

import com.example.common.resource.Resource
import com.example.domain.models.EpisodesModel
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {

    fun fetchEpisodes(page: Int): Flow<Resource<List<EpisodesModel>>>

    fun fetchEpisode(id: Int): Flow<Resource<EpisodesModel>>
}