package com.example.domain.repositories

import com.example.common.resource.Resource
import com.example.domain.models.ApiModel
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    fun fetchCharacter(name: String): Flow<Resource<List<ApiModel>>>

    fun fetchEpisodes(name: String): Flow<Resource<List<ApiModel>>>

    fun fetchLocations(name: String): Flow<Resource<List<ApiModel>>>

}