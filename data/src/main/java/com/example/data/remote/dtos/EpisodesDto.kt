package com.example.data.remote.dtos

import com.example.domain.models.EpisodesModel
import com.google.gson.annotations.SerializedName

data class EpisodesDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val air_date: String,
    @SerializedName("episode")
    val episode: String,
    @SerializedName("created")
    val created: String
)

fun EpisodesDto.toDomain() = EpisodesModel(
    id, name, air_date, episode,created
)