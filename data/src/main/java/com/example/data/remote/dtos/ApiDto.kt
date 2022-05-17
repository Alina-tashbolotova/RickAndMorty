package com.example.data.remote.dtos

import com.example.domain.models.ApiModel
import com.google.gson.annotations.SerializedName

data class ApiDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("species")
    val species: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("air_date")
    val air_date: String? = null,
    @SerializedName("dimension")
    val dimension: String? = null,
    @SerializedName("created")
    val created: String? = null,
)

fun ApiDto.toDomain() = ApiModel(
    id, name, image, status, species, type, gender, air_date, dimension, created
)
