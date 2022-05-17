package com.example.rickandmorty.presentation.ui.fragments.characters.detail

import com.example.domain.usecases.FetchCharacterUseCase
import com.example.rickandmorty.presentation.base.BaseViewModel
import com.example.rickandmorty.presentation.models.CharactersUI
import com.example.rickandmorty.presentation.models.toUI
import com.example.rickandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val fetchCharacterUseCase: FetchCharacterUseCase) :
    BaseViewModel() {
    private val _stateCharacterDetail = MutableStateFlow<UIState<CharactersUI>>(UIState.Loading())
    val stateCharacterDetail: StateFlow<UIState<CharactersUI>> = _stateCharacterDetail

    fun fetchCharacter(id: Int) {
        fetchCharacterUseCase(id).collectRequest(_stateCharacterDetail) { it.toUI() }
    }
}