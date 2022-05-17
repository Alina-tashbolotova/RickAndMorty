package com.example.data.remote.dtos

import com.example.domain.models.LocationsModel
import com.google.gson.annotations.SerializedName

data class LocationsDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("created")
    val created: String,
    )

fun LocationsDto.toDomain() = LocationsModel(
    id, name, type, dimension,created
)