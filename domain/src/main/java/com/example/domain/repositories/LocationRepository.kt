package com.example.domain.repositories

import com.example.common.resource.Resource
import com.example.domain.models.LocationsModel
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    fun fetchLocations(page: Int): Flow<Resource<List<LocationsModel>>>

    fun fetchLocation(id: Int): Flow<Resource<LocationsModel>>
}