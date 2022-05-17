package com.example.rickandmorty.presentation.ui.fragments.characters.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.rickandmorty.databinding.FragmentCharacterImageDialogBinding

class CharacterImageDialog : DialogFragment() {

    private var _binding: FragmentCharacterImageDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentCharacterImageDialogBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(activity)
            .setView(binding.root)
            .create()
        builder.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setupArgsDialog()
        return builder
    }

    private fun setupArgsDialog() = with(binding) {
        Glide.with(imageDialogCharacter)
            .load(CharacterImageDialogArgs.fromBundle(
                requireArguments()).image)
            .into(imageDialogCharacter)
    }

}