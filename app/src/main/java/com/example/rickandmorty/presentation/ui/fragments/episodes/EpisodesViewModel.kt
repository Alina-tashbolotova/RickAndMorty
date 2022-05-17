package com.example.rickandmorty.presentation.ui.fragments.episodes

import com.example.domain.usecases.FetchEpisodesUseCase
import com.example.rickandmorty.presentation.base.BaseRequest
import com.example.rickandmorty.presentation.base.BaseViewModel
import com.example.rickandmorty.presentation.models.EpisodesUI
import com.example.rickandmorty.presentation.models.toUI
import com.example.rickandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(private val fetchEpisodesUseCase: FetchEpisodesUseCase) :
    BaseViewModel(), BaseRequest {

    private val _dataEpisode = MutableStateFlow<UIState<List<EpisodesUI>>>(UIState.Loading())
    val dataEpisode: StateFlow<UIState<List<EpisodesUI>>> = _dataEpisode

    override var page: Int = 1
    var count = 0

    init {
        fetchEpisodes(page)
    }

    override fun fetchEpisodes(page: Int) {
        fetchEpisodesUseCase(page).collectRequest(_dataEpisode) { it.map { data -> data.toUI() } }
    }

    override fun fetchLocations(page: Int) {
    }

    override fun fetchCharacters(page: Int) {
    }
}