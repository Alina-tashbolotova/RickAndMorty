package com.example.rickandmorty.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemLocationsBinding
import com.example.rickandmorty.presentation.base.BaseComparator
import com.example.rickandmorty.presentation.models.LocationsUI


class LocationsAdapter(
    private val onItemClick: (id: Int, name: String) -> Unit,
) : ListAdapter<LocationsUI, LocationsAdapter.LocationsViewHolder>(
    BaseComparator()
) {

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        return LocationsViewHolder(ItemLocationsBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    inner class LocationsViewHolder(private val binding: ItemLocationsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    onItemClick(it.id, it.name)
                }
            }
        }

        fun onBind(model: LocationsUI) = with(binding) {
            mainTitle.text = model.name
            descriptionText.text = model.created
            itemLocationDimension.text = model.dimension
        }
    }
}