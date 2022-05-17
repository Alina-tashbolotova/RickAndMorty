package com.example.rickandmorty.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ItemCharactersBinding
import com.example.rickandmorty.databinding.ItemEpisodesBinding
import com.example.rickandmorty.databinding.ItemLocationsBinding
import com.example.rickandmorty.presentation.base.BaseComparator
import com.example.rickandmorty.presentation.models.ApiUI

class ApiAdapter : ListAdapter<ApiUI, RecyclerView.ViewHolder>(BaseComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_characters -> CharacterViewHolder(ItemCharactersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
            R.layout.item_episodes -> EpisodeViewHolder(ItemEpisodesBinding.inflate(LayoutInflater.from(
                parent.context), parent, false))
            R.layout.item_locations -> LocationViewHolder(ItemLocationsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
            else -> throw IllegalArgumentException("View type not found: $viewType")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_characters -> getItem(position)?.let {
                (holder as CharacterViewHolder).onBind(it)
            }
            R.layout.item_episodes -> getItem(position)?.let {
                (holder as EpisodeViewHolder).onBind(it)
            }
            R.layout.item_locations -> getItem(position)?.let {
                (holder as LocationViewHolder).onBind(it)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            getItem(position).air_date != null -> {
                R.layout.item_episodes
            }
            getItem(position).dimension != null -> {
                R.layout.item_locations
            }
            else -> {
                R.layout.item_characters
            }
        }
    }


    inner class CharacterViewHolder(private val binding: ItemCharactersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(model: ApiUI) {
            binding.nameCharacterText.text = model.name
            binding.imageCharacterImage.load(model.image)
        }
    }


    inner class EpisodeViewHolder(private val binding: ItemEpisodesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(model: ApiUI) {
            binding.txtName.text = model.name
        }
    }

    inner class LocationViewHolder(private val binding: ItemLocationsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(model: ApiUI) {
            binding.mainTitle.text = model.name
        }
    }


}