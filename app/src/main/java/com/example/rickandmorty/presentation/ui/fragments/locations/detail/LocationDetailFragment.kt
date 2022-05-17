package com.example.rickandmorty.presentation.ui.fragments.locations.detail

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentLocationDetailBinding
import com.example.rickandmorty.presentation.base.BaseFragment
import com.example.rickandmorty.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailFragment :
    BaseFragment<LocationDetailViewModel, FragmentLocationDetailBinding>(R.layout.fragment_location_detail) {

    override val binding by viewBinding(FragmentLocationDetailBinding::bind)
    override val viewModel: LocationDetailViewModel by viewModels()

    override fun initialize() = with(binding) {
        viewModel.fetchLocation(LocationDetailFragmentArgs.fromBundle(requireArguments()).id)

    }

    override fun setupSubscribes() = with(binding) {
        viewModel.stateLocationsDetail.collectUIState {
            when (it) {
                is UIState.Error -> {
                }
                is UIState.Loading -> {
                    progress.isVisible = false
                }
                is UIState.Success -> {
                    locationNameDetail.text = it.data.name
                    locationDetailDimension.text = it.data.dimension
                    locationDetailType.text = it.data.type
                }
            }
        }
    }
}
