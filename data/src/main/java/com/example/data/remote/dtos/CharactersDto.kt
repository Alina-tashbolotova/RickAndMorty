package com.example.data.remote.dtos

import com.example.domain.models.CharactersModel
import com.google.gson.annotations.SerializedName

data class CharactersDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("created")
    val created: String,
)

fun CharactersDto.toDomain() = CharactersModel(
    id,
    name,
    image,
    status,
    species,
    type,
    gender,
    created
)

