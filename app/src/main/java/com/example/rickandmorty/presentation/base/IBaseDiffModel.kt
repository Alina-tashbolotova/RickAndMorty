package com.example.rickandmorty.presentation.base

interface IBaseDiffModel {

    val id: Int?
    override fun equals(other:Any?):Boolean

}