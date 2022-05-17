package com.example.domain.usecases

import com.example.domain.repositories.CharactersRepository
import javax.inject.Inject

class FetchSearchUseCase @Inject constructor(
    private val repository: CharactersRepository,
) {
    operator fun invoke(name: String, page: Int) = repository.fetchCharacterBySearch(name, page)
}