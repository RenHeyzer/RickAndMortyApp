package com.example.rickandmortyapp.domain.models.character

import com.example.rickandmortyapp.base.IBaseDiffModel

data class CharactersModel(
    val image: String?,
    val gender: String?,
    val species: String?,
    val created: String?,
    val origin: Origin,
    val name: String?,
    val location: Location,
    val episode: List<String>?,
    override val id: Int?,
    val type: String?,
    val url: String?,
    val status: String?
) : IBaseDiffModel