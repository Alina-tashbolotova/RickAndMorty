package com.example.domain.usecases

import com.example.domain.repositories.CharactersRepository
import javax.inject.Inject

class FetchGenderAndStatusUseCase @Inject constructor(
    private val repository: CharactersRepository,
) {
    operator fun invoke(gender: String?, status: String?, page: Int) =
        repository.fetchCharactersByGender(page, gender, status)
}