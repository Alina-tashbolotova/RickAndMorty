package com.example.rickandmorty.presentation.ui.fragments.episodes.detail

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentEpisodeDetailBinding
import com.example.rickandmorty.presentation.base.BaseFragment
import com.example.rickandmorty.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetailFragment :
    BaseFragment<EpisodeDetailViewModel, FragmentEpisodeDetailBinding>(R.layout.fragment_episode_detail) {

    override val binding by viewBinding(FragmentEpisodeDetailBinding::bind)
    override val viewModel: EpisodeDetailViewModel by viewModels()

    override fun initialize() = with(binding) {
        viewModel.fetchEpisode(EpisodeDetailFragmentArgs.fromBundle(requireArguments()).id)
    }

    override fun setupSubscribes() = with(binding) {
        viewModel.stateEpisodeDetail.collectUIState {
            when (it) {
                is UIState.Error -> {
                }
                is UIState.Loading -> {
                    progress.isVisible = false
                }
                is UIState.Success -> {
                    episodeDetailName.text = it.data.name
                    episodeAirDate.text = it.data.air_date
                    episodeCreated.text = it.data.episode
                }
            }
        }
    }

}