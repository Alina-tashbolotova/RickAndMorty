package com.example.data.repositories

import com.example.data.remote.apiservices.LocationsApi
import com.example.data.remote.dtos.toDomain
import com.example.data.repositories.base.BaseRepository
import com.example.domain.repositories.LocationRepository
import javax.inject.Inject


class LocationsRepositoryImpl @Inject constructor(private val service: LocationsApi) :
    BaseRepository(),
    LocationRepository {

    override fun fetchLocations(page: Int) = doRequest {
        service.fetchLocations(page).results.map { it.toDomain() }
    }

    override fun fetchLocation(id: Int) = doRequest {
        service.fetchLocation(id).toDomain()
    }


}