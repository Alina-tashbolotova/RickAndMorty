package com.example.domain.usecases

import com.example.domain.repositories.LocationRepository
import javax.inject.Inject

class FetchLocationUseCase @Inject constructor(private val repository: LocationRepository) {

    operator fun invoke(id: Int) = repository.fetchLocation(id)
}