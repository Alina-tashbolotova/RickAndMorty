package com.example.rickandmorty.presentation.ui.fragments.api

import com.example.domain.usecases.FetchCharacterApiUseCase
import com.example.domain.usecases.FetchEpisodeApiUseCase
import com.example.domain.usecases.FetchLocationApiUseCase
import com.example.rickandmorty.presentation.base.BaseViewModel
import com.example.rickandmorty.presentation.models.ApiUI
import com.example.rickandmorty.presentation.models.toUI
import com.example.rickandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(
    private val fetchCharacterAllUseCase: FetchCharacterApiUseCase,
    private val fetchEpisodeAllUseCase: FetchEpisodeApiUseCase,
    private val fetchLocationAllUseCase: FetchLocationApiUseCase,
) : BaseViewModel() {

    private val _stateFilterCharacters = MutableStateFlow<UIState<List<ApiUI>>>(UIState.Loading())
    val stateFilterCharacters: StateFlow<UIState<List<ApiUI>>> = _stateFilterCharacters

    private val _stateFilterEp = MutableStateFlow<UIState<List<ApiUI>>>(UIState.Loading())
    val stateFilterEp: StateFlow<UIState<List<ApiUI>>> = _stateFilterEp

    private val _stateFilterLoc = MutableStateFlow<UIState<List<ApiUI>>>(UIState.Loading())
    val stateFilterLoc: StateFlow<UIState<List<ApiUI>>> = _stateFilterLoc


    fun fetchSearch(name: String) {
        fetchCharacterAllUseCase(name).collectRequest(_stateFilterCharacters) { it.map { data -> data.toUI() } }
    }

    fun fetchLocation(name: String) {
        fetchLocationAllUseCase(name).collectRequest(_stateFilterLoc) { it.map { data -> data.toUI() } }

    }

    fun fetchEpisodes(name: String) {
        fetchEpisodeAllUseCase(name).collectRequest(_stateFilterEp) { it.map { data -> data.toUI() } }

    }

}