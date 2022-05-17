package com.example.rickandmorty.presentation.ui.fragments.episodes

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentEpisodesBinding
import com.example.rickandmorty.presentation.base.BaseFragment
import com.example.rickandmorty.presentation.extensions.scrollListenNextPageEpisodes
import com.example.rickandmorty.presentation.models.EpisodesUI
import com.example.rickandmorty.presentation.state.UIState
import com.example.rickandmorty.presentation.ui.adapters.EpisodesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment :
    BaseFragment<EpisodesViewModel, FragmentEpisodesBinding>(R.layout.fragment_episodes) {

    private val episodeAdapter = EpisodesAdapter(this::setupListener)
    override val binding by viewBinding(FragmentEpisodesBinding::bind)
    override val viewModel: EpisodesViewModel by viewModels()

    override fun initialize() = with(binding) {
        rvEpisode.layoutManager = LinearLayoutManager(requireContext())
        rvEpisode.adapter = episodeAdapter
    }

    override fun scrollListener() = with(binding) {
        rvEpisode.scrollListenNextPageEpisodes(viewModel)
    }

    override fun setupSubscribes() = with(binding) {
        viewModel.dataEpisode.collectUIState {
            when (it) {
                is UIState.Error -> {
                }
                is UIState.Loading -> {
                    progress.isVisible = true
                }
                is UIState.Success -> {
                    val list = ArrayList<EpisodesUI>(episodeAdapter.currentList)
                    it.data.let { data -> list.addAll(data) }
                    episodeAdapter.submitList(list)
                    progress.isVisible = false
                }
            }
        }
    }

    private fun setupListener(id: Int, name: String) {
        findNavController().navigate(EpisodesFragmentDirections.actionNavigationEpisodesToEpisodeDetailFragment(
            name,
            id))
    }
}