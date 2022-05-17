package com.example.rickandmorty.presentation.ui.fragments.locations.detail

import com.example.domain.usecases.FetchLocationUseCase
import com.example.rickandmorty.presentation.base.BaseViewModel
import com.example.rickandmorty.presentation.models.LocationsUI
import com.example.rickandmorty.presentation.models.toUI
import com.example.rickandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(private val locationUseCase: FetchLocationUseCase) :
    BaseViewModel() {

    private val _stateLocationsDetail = MutableStateFlow<UIState<LocationsUI>>(UIState.Loading())
    val stateLocationsDetail: StateFlow<UIState<LocationsUI>> = _stateLocationsDetail

    fun fetchLocation(id: Int) {
        locationUseCase(id).collectRequest(_stateLocationsDetail) { it.toUI() }
    }
}