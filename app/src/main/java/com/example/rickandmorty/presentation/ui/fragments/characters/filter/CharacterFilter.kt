package com.example.rickandmorty.presentation.ui.fragments.characters.filter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.CharacterFilterBinding
import com.example.rickandmorty.presentation.extensions.getText
import com.example.rickandmorty.presentation.models.Filter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFilter : DialogFragment() {

    private var _binding: CharacterFilterBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = CharacterFilterBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(activity)
            .setView(binding.root)
            .create()
        builder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setupSubscribes()
        setupListeners()
        return builder
    }

    private fun setupListeners() {
        resetCharacter()
    }

    private fun resetCharacter() {
        binding.btnReset.setOnClickListener {
            findNavController().navigate(R.id.navigation_characters)
        }
    }

    private fun setupSubscribes() = with(binding) {
        btnMakeFilter.setOnClickListener {
            val genderText = groupGender.getText()
            val statusText = groupStatus.getText()
            val filterData = Filter(status = statusText, gender = genderText)
            val bundle = Bundle()
            bundle.putParcelable("data", filterData)
            findNavController().navigate(R.id.navigation_characters, bundle)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}