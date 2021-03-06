package com.example.rickandmorty.presentation.extensions

import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.presentation.base.BaseRequest

fun RecyclerView.scrollListenNextPageCharacters(viewModel: BaseRequest) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                viewModel.page++
                viewModel.fetchCharacters(viewModel.page)
            }
        }
    })
}

fun RecyclerView.scrollListenNextPageEpisodes(viewModel: BaseRequest) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                viewModel.page++
                viewModel.fetchEpisodes(viewModel.page)
            }
        }
    })
}

fun RecyclerView.scrollListenNextPageLocations(viewModel: BaseRequest) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                viewModel.page++
                viewModel.fetchLocations(viewModel.page)
            }
        }
    })
}