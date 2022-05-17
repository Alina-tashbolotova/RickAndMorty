package com.example.domain.usecases

import com.example.domain.repositories.ApiRepository
import javax.inject.Inject

class FetchLocationApiUseCase @Inject constructor(
    private val repository: ApiRepository,
) {
    operator fun invoke(name: String) = repository.fetchLocations(name)
}