package com.example.rickandmorty.presentation.ui.fragments.locations

import com.example.domain.usecases.FetchLocationsUseCase
import com.example.rickandmorty.presentation.base.BaseRequest
import com.example.rickandmorty.presentation.base.BaseViewModel
import com.example.rickandmorty.presentation.models.LocationsUI
import com.example.rickandmorty.presentation.models.toUI
import com.example.rickandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(private val locationsUseCase: FetchLocationsUseCase) :
    BaseViewModel(), BaseRequest {

    private val _stateLocationsDetail =
        MutableStateFlow<UIState<List<LocationsUI>>>(UIState.Loading())
    val stateLocations: StateFlow<UIState<List<LocationsUI>>> = _stateLocationsDetail
    override var page: Int = 1
    var count = 0

    init {
        fetchLocations(page)
    }

    override fun fetchLocations(page: Int) {
        locationsUseCase(page).collectRequest(_stateLocationsDetail) { it.map { data -> data.toUI() } }
    }

    override fun fetchCharacters(page: Int) {
    }

    override fun fetchEpisodes(page: Int) {
    }
}