package com.example.domain.models

data class CharactersModel(
    var id: Int,
    var name: String,
    var image: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    val created: String,
    var firstSeenIn: String = ""
)
