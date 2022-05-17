package com.example.rickandmorty.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Filter(
    val alive: String? = null,
    val gender: String? = null,
    val status: String? = null,
) : Parcelable
