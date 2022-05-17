package com.example.rickandmorty.presentation.base

interface BaseRequest {
    var page: Int
    fun fetchCharacters(page: Int)
    fun fetchEpisodes(page: Int)
    fun fetchLocations(page: Int)
}