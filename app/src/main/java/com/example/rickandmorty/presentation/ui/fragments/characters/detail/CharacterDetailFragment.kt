package com.example.rickandmorty.presentation.ui.fragments.characters.detail

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.presentation.base.BaseFragment
import com.example.rickandmorty.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<CharacterDetailViewModel, FragmentCharacterDetailBinding>(R.layout.fragment_character_detail) {

    override val binding by viewBinding(FragmentCharacterDetailBinding::bind)
    override val viewModel: CharacterDetailViewModel by viewModels()

    override fun initialize() {
        viewModel.fetchCharacter(CharacterDetailFragmentArgs.fromBundle(requireArguments()).id)
    }

    override fun setupSubscribes() = with(binding) {
        viewModel.stateCharacterDetail.collectUIState {
            when (it) {
                is UIState.Error -> {
                }
                is UIState.Loading -> {
                    progress.isVisible = false
                }
                is UIState.Success -> {

                    when (it.data.status) {
                        "Alive" -> indicator.setImageResource(R.drawable.alive)
                        "Dead" -> indicator.setImageResource(R.drawable.dead)
                        else -> indicator.setImageResource(R.drawable.unknown)
                    }
                    txtName.text = it.data.name
                    txtGender.text = it.data.gender
                    txtSpecies.text = it.data.species
                    txtStatus.text = it.data.status
                    imgImage.load(it.data.image)
                }
            }
        }
    }
}