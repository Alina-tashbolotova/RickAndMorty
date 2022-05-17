package com.example.rickandmorty.presentation.ui.fragments.api

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentApiBinding
import com.example.rickandmorty.presentation.base.BaseFragment
import com.example.rickandmorty.presentation.models.ApiUI
import com.example.rickandmorty.presentation.state.UIState
import com.example.rickandmorty.presentation.ui.adapters.ApiAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApiFragment : BaseFragment<ApiViewModel, FragmentApiBinding>(R.layout.fragment_api) {

    override val binding by viewBinding(FragmentApiBinding::bind)
    override val viewModel: ApiViewModel by viewModels()
    private val allAdapter = ApiAdapter()
    private val lst: ArrayList<ApiUI> = ArrayList()
    private var currentText = ""

    override fun initialize() = with(binding) {
        rvApi.layoutManager = LinearLayoutManager(requireContext())
        rvApi.adapter = allAdapter
    }

    override fun setupListeners() {
        searchAll()
    }

    private fun searchAll() {
        val handler = Handler(Looper.getMainLooper())
        val searchRunnable = Runnable {
            viewModel.fetchSearch(currentText)
            viewModel.fetchLocation(currentText)
            viewModel.fetchEpisodes(currentText)
        }
        binding.search.doAfterTextChanged {
            lst.clear()
            currentText = it?.toString() ?: ""
            handler.removeCallbacks(searchRunnable)
            handler.postDelayed(searchRunnable, 500L)
        }
    }

    override fun setupRequests() {
        viewModel.stateFilterEp.collectUIState {
            when (it) {
                is UIState.Error -> {
                }
                is UIState.Loading -> {
                }
                is UIState.Success -> {
                    lst.addAll(it.data)
                    allAdapter.notifyDataSetChanged()
                    allAdapter.submitList(lst.sortedByDescending { data -> data.created })
                }
            }
        }

        viewModel.stateFilterCharacters.collectUIState {
            when (it) {
                is UIState.Error -> {
                }
                is UIState.Loading -> {
                }
                is UIState.Success -> {
                    lst.addAll(it.data)
                    allAdapter.notifyDataSetChanged()
                    allAdapter.submitList(lst.sortedByDescending { data -> data.created })
                }
            }
        }

        viewModel.stateFilterLoc.collectUIState {
            when (it) {
                is UIState.Error -> {
                }
                is UIState.Loading -> {
                }
                is UIState.Success -> {
                    lst.addAll(it.data)
                    allAdapter.notifyDataSetChanged()
                    allAdapter.submitList(lst.sortedByDescending { data -> data.created })
                }
            }
        }
    }
}