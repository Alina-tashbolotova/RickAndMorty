package com.example.data.remote.apiservices

import com.example.data.remote.dtos.LocationsDto
import com.example.data.remote.dtos.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationsApi {
    @GET("location")
    suspend fun fetchLocations(
        @Query("page") page: Int,
    ): RickAndMortyResponse<LocationsDto>

    @GET("location/{id}")
    suspend fun fetchLocation(@Path("id") id: Int): LocationsDto
}