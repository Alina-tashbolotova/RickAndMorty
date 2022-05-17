package com.example.data.remote.apiservices

import com.example.data.remote.dtos.CharactersDto
import com.example.data.remote.dtos.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {
    @GET("character")
    suspend fun fetchCharacters(
        @Query("page") page: Int,
    ): RickAndMortyResponse<CharactersDto>

    @GET("character/{id}")
    suspend fun fetchCharacter(
        @Path("id") id: Int,
    ): CharactersDto

    @GET("character")
    suspend fun fetchSearch(
        @Query("name") name: String,
        @Query("page") page: Int,
    ): RickAndMortyResponse<CharactersDto>

    @GET("character")
    suspend fun fetchGenderAndStatus(
        @Query("gender") gender: String?,
        @Query("status") status: String?,
        @Query("page") page: Int,
    ): RickAndMortyResponse<CharactersDto>
}