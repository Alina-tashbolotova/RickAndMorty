package com.example.rickandmorty.presentation.models

import com.example.domain.models.LocationsModel
import com.example.rickandmorty.presentation.base.IBaseDiffModel

data class LocationsUI(
    override var id: Int,
    var name: String,
    var type: String,
    val dimension: String,
    val created: String,
) : IBaseDiffModel

fun LocationsModel.toUI() = LocationsUI(
    id, name, type, dimension, created
)