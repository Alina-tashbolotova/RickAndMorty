package com.example.rickandmorty.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemEpisodesBinding
import com.example.rickandmorty.presentation.base.BaseComparator
import com.example.rickandmorty.presentation.models.EpisodesUI


class EpisodesAdapter(
    private val onItemClick: (id: Int, name: String) -> Unit,
) : ListAdapter<EpisodesUI, EpisodesAdapter.EpisodesViewHolder>(
    BaseComparator()
) {

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        return EpisodesViewHolder(ItemEpisodesBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    inner class EpisodesViewHolder(private val binding: ItemEpisodesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    onItemClick(it.id, it.name)
                }
            }
        }

        fun onBind(model: EpisodesUI) = with(binding) {
            txtName.text = model.name
            descName.text = model.episode
        }
    }
}