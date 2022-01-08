package com.example.rickandmortyapp.base

interface IBaseDiffModel {
    val id: Int?
    override fun equals(other: Any?): Boolean
}