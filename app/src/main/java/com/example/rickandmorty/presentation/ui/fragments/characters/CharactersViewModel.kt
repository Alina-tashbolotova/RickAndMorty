package com.example.rickandmorty.presentation.ui.fragments.characters

import com.example.domain.usecases.FetchCharactersUseCase
import com.example.domain.usecases.FetchGenderAndStatusUseCase
import com.example.domain.usecases.FetchSearchUseCase
import com.example.rickandmorty.presentation.base.BaseRequest
import com.example.rickandmorty.presentation.base.BaseViewModel
import com.example.rickandmorty.presentation.models.CharactersUI
import com.example.rickandmorty.presentation.models.toUI
import com.example.rickandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val fetchCharacterByGenderAndStatusUseCase: FetchGenderAndStatusUseCase,
    private val fetchCharacterBySearchUseCase: FetchSearchUseCase,
) : BaseViewModel(), BaseRequest {

    private val _stateCharacters = MutableStateFlow<UIState<List<CharactersUI>>>(UIState.Loading())
    val stateCharacters: StateFlow<UIState<List<CharactersUI>>> = _stateCharacters
    private val _stateFilterCharacters =
        MutableStateFlow<UIState<List<CharactersUI>>>(UIState.Loading())
    val stateFilterCharacters: StateFlow<UIState<List<CharactersUI>>> = _stateFilterCharacters

    override var page: Int = 1
    var count = 0

    init {
        fetchCharacters(page)
    }

    override fun fetchCharacters(page: Int) {
        fetchCharactersUseCase(page).collectRequest(_stateCharacters) { it.map { data -> data.toUI() } }

    }

//    fun fetchCharacterBySearch(name: String, page: Int = 1) {
//        fetchCharacterBySearchUseCase(name,
//            page).collectRequest(_stateFilterCharacters) { it.map { data -> data.toUI() } }
//    }


    fun fetchGenderAndStatus(gender: String?, page: Int, status: String?) {
        fetchCharacterByGenderAndStatusUseCase(gender, status, page).collectRequest(
            _stateFilterCharacters) { it.map { data -> data.toUI() } }
    }

    override fun fetchEpisodes(page: Int) {
    }

    override fun fetchLocations(page: Int) {
    }
}