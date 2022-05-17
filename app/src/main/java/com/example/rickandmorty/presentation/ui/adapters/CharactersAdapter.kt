package com.example.rickandmorty.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ItemCharactersBinding
import com.example.rickandmorty.presentation.base.BaseComparator
import com.example.rickandmorty.presentation.models.CharactersUI


class CharactersAdapter(
    private val onItemClick: (id: Int, name: String) -> Unit,
    private val onLongClickListener: (image: String) -> Unit,
) : ListAdapter<CharactersUI, CharactersAdapter.ViewHolder>(
    BaseComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCharactersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class ViewHolder(private val binding: ItemCharactersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    onItemClick(it.id, it.name)
                }
            }
            binding.root.setOnLongClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    onLongClickListener(it.image)
                }
                return@setOnLongClickListener false
            }
        }

        fun onBind(model: CharactersUI) = with(binding) {
            nameCharacterText.text = model.name
            imageCharacterImage.load(model.image)
            itemGender.text = model.gender
            itemCharacterSpecies.text = model.species
            when (model.status) {
                "Alive" -> itemStatusRectangle.setImageResource(R.drawable.alive)
                "Dead" -> itemStatusRectangle.setImageResource(R.drawable.dead)
                else -> itemStatusRectangle.setImageResource(R.drawable.unknown)
            }

        }
    }
}