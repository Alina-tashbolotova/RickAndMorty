package com.example.rickandmorty.presentation.ui.fragments.characters

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.presentation.base.BaseFragment
import com.example.rickandmorty.presentation.models.CharactersUI
import com.example.rickandmorty.presentation.models.Filter
import com.example.rickandmorty.presentation.state.UIState
import com.example.rickandmorty.presentation.ui.adapters.CharactersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment :
    BaseFragment<CharactersViewModel, FragmentCharactersBinding>(R.layout.fragment_characters) {

    override val binding by viewBinding(FragmentCharactersBinding::bind)
    override val viewModel: CharactersViewModel by viewModels()
    private val charactersAdapter =
        CharactersAdapter(this::setupListeners, this::setupLongListeners)
    private var filterData: Filter? = Filter()


    override fun initialize() = with(binding) {
        rvCharacter.layoutManager = LinearLayoutManager(requireContext())
        rvCharacter.adapter = charactersAdapter
        filterData = arguments?.getParcelable("data")

    }

    override fun setupSubscribes() = with(binding) {
        if (filterData == null) {
            viewModel.stateCharacters.collectUIState {
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
                        val list = ArrayList<CharactersUI>(charactersAdapter.currentList)
                        it.data.let { data -> list.addAll(data) }
                        charactersAdapter.submitList(list)
                        binding.progress.isVisible = false
                        binding.progressPage.isVisible = false
                    }
                }
            }

        } else {
            viewModel.fetchGenderAndStatus(filterData?.gender,
                viewModel.page,
                filterData?.alive)
            viewModel.stateFilterCharacters.collectUIState {
                when (it) {
                    is UIState.Error -> {
                    }
                    is UIState.Loading -> {
                    }
                    is UIState.Success -> {
                        val list = ArrayList<CharactersUI>(charactersAdapter.currentList)
                        it.data.let { data -> list.addAll(data) }
                        charactersAdapter.submitList(list)
                    }
                }
            }
        }
    }


    override fun scrollListener() = with(binding) {
        rvCharacter.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (filterData != null) {
                        viewModel.page++
                        viewModel.fetchGenderAndStatus(filterData?.gender,
                            viewModel.page,
                            filterData?.alive)
                    } else {
                        viewModel.page++
                        viewModel.fetchCharacters(viewModel.page)
                    }

                }
            }

        })
    }

    override fun setupListeners() {
        binding.btnFilter.setOnClickListener {
            findNavController().navigate(R.id.characterFilter)
        }
    }

    private fun setupListeners(id: Int, name: String) {
        findNavController().navigate(
            CharactersFragmentDirections.actionNavigationCharactersToCharacterDetailFragment(
                name, id
            )
        )
    }

    private fun setupLongListeners(image: String) {
        findNavController().navigate(CharactersFragmentDirections.actionNavigationCharactersToCharacterImageDialog(
            image))
    }


}