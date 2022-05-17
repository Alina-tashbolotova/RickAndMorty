package com.example.rickandmorty.presentation.ui.fragments.locations

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentLocationsBinding
import com.example.rickandmorty.presentation.base.BaseFragment
import com.example.rickandmorty.presentation.models.LocationsUI
import com.example.rickandmorty.presentation.state.UIState
import com.example.rickandmorty.presentation.ui.adapters.LocationsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationsFragment :
    BaseFragment<LocationsViewModel, FragmentLocationsBinding>(R.layout.fragment_locations) {

    override val binding by viewBinding(FragmentLocationsBinding::bind)
    override val viewModel: LocationsViewModel by viewModels()
    private val locationAdapter = LocationsAdapter(this::setupListener)

    override fun initialize() = with(binding) {
        rvLocation.layoutManager = LinearLayoutManager(requireContext())
        rvLocation.adapter = locationAdapter
    }

    override fun setupSubscribes() = with(binding) {
        viewModel.stateLocations.collectUIState {
            when (it) {
                is UIState.Error -> {
                }
                is UIState.Loading -> {
                    if (viewModel.count == 0) {
                        progress.isVisible = true
                        viewModel.count++
                    } else {
                        progressPage.isVisible = true
                    }
                }
                is UIState.Success -> {
                    val list = ArrayList<LocationsUI>(locationAdapter.currentList)
                    it.data.let { data -> list.addAll(data) }
                    locationAdapter.submitList(list)
                    progress.isVisible = false
                    progressPage.isVisible = false
                }
            }
        }
    }

    private fun setupListener(id: Int, name: String) {
        findNavController().navigate(LocationsFragmentDirections.actionNavigationLocationsToLocationDetailFragment(
            name,
            id))
    }

}