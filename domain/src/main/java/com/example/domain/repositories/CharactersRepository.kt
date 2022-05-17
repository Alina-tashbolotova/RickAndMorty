package com.example.domain.repositories

import com.example.common.resource.Resource
import com.example.domain.models.CharactersModel
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun fetchCharacters(page: Int): Flow<Resource<List<CharactersModel>>>

    fun fetchCharacter(id: Int): Flow<Resource<CharactersModel>>

    fun fetchCharactersByGender(
        page: Int,
        gender: String?,
        status: String?
    ): Flow<Resource<List<CharactersModel>>>

    fun fetchCharacterBySearch(name: String, page: Int): Flow<Resource<List<CharactersModel>>>

}