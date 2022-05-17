package com.example.data.remote.apiservices

import com.example.data.remote.dtos.EpisodesDto
import com.example.data.remote.dtos.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodesApi {
    @GET("episode")
    suspend fun fetchEpisodes(
        @Query("page") page: Int,
    ): RickAndMortyResponse<EpisodesDto>

    @GET("episode/{id}")
    suspend fun fetchEpisode(@Path("id") id: Int): EpisodesDto
}