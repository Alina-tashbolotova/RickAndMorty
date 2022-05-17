package com.example.rickandmorty.presentation.ui.fragments.episodes.detail

import com.example.domain.usecases.FetchEpisodeUseCase
import com.example.rickandmorty.presentation.base.BaseViewModel
import com.example.rickandmorty.presentation.models.EpisodesUI
import com.example.rickandmorty.presentation.models.toUI
import com.example.rickandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(private val episodeUseCase: FetchEpisodeUseCase) :
    BaseViewModel() {

    private val _stateEpisodeDetail = MutableStateFlow<UIState<EpisodesUI>>(UIState.Loading())
    val stateEpisodeDetail: StateFlow<UIState<EpisodesUI>> = _stateEpisodeDetail


    fun fetchEpisode(id: Int) {
        episodeUseCase(id).collectRequest(_stateEpisodeDetail) { it.toUI() }
    }
}