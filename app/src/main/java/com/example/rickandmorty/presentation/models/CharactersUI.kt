package com.example.rickandmorty.presentation.models

import com.example.domain.models.CharactersModel
import com.example.rickandmorty.presentation.base.IBaseDiffModel

data class CharactersUI(
    override var id: Int,
    var name: String,
    var image: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    val created: String,
    var firstSeenIn: String = "",
) : IBaseDiffModel

fun CharactersModel.toUI() = CharactersUI(
    id, name, image, status, species, type, gender, created
)
