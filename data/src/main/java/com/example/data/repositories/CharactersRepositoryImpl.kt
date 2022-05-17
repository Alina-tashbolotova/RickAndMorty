package com.example.data.repositories


import com.example.data.remote.apiservices.CharactersApi
import com.example.data.remote.dtos.toDomain
import com.example.data.repositories.base.BaseRepository
import com.example.domain.repositories.CharactersRepository
import javax.inject.Inject


class CharactersRepositoryImpl @Inject constructor(
    private val service: CharactersApi,
) : BaseRepository(), CharactersRepository {

    override fun fetchCharacters(page: Int) = doRequest {
        service.fetchCharacters(page).results.map { it.toDomain() }
    }

    override fun fetchCharacter(id: Int) = doRequest {
        service.fetchCharacter(id).toDomain()
    }

    override fun fetchCharactersByGender(
        page: Int,
        gender: String?,
        status: String?,
    ) = doRequest {
        service.fetchGenderAndStatus(gender, status, page).results.map { it.toDomain() }
    }

    override fun fetchCharacterBySearch(name: String, page: Int) = doRequest {
        service.fetchSearch(name, page).results.map { it.toDomain() }
    }
}

