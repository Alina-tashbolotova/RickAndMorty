package com.example.rickandmorty.presentation.models

import com.example.domain.models.ApiModel
import com.example.rickandmorty.presentation.base.IBaseDiffModel

data class ApiUI(
    override val id: Int? = null,
    val name: String? = null,
    val image: String? = null,
    val status: String? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: String? = null,
    val air_date: String? = null,
    val dimension: String? = null,
    val created: String? = null,
) : IBaseDiffModel

fun ApiModel.toUI() = ApiUI(
    id, name, image, status, species, type, gender, air_date, dimension, created
)
