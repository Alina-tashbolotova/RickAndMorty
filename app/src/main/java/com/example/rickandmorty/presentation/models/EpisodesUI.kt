package com.example.rickandmorty.presentation.models

import com.example.domain.models.EpisodesModel
import com.example.rickandmorty.presentation.base.IBaseDiffModel

data class EpisodesUI(
    override var id: Int,
    var name: String,
    var episode: String,
    val air_date: String,
    val created: String
) : IBaseDiffModel

fun EpisodesModel.toUI() = EpisodesUI(
    id, name, episode, air_date, created
)