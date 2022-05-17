package com.example.data.remote.apiservices

import com.example.data.remote.dtos.ApiDto
import com.example.data.remote.dtos.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FilterApi {
    @GET("/api/character")
    suspend fun fetchCharacters(@Query("name") name: String): RickAndMortyResponse<ApiDto>

    @GET("/api/episode")
    suspend fun fetchEpisodes(@Query("name") name: String): RickAndMortyResponse<ApiDto>

    @GET("/api/location")
    suspend fun fetchLocation(@Query("name") name: String): RickAndMortyResponse<ApiDto>
}

