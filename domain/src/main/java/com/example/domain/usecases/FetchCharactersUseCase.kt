package com.example.domain.usecases

import com.example.domain.repositories.CharactersRepository
import javax.inject.Inject

class FetchCharactersUseCase @Inject constructor(private val repository: CharactersRepository) {

    operator fun invoke(page: Int) = repository.fetchCharacters(page)
}