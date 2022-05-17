package com.example.domain.usecases

import com.example.domain.repositories.CharactersRepository
import javax.inject.Inject

class FetchCharacterUseCase @Inject constructor(private val repository: CharactersRepository) {

    operator fun invoke(id: Int) = repository.fetchCharacter(id)
}