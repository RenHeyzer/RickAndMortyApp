package com.example.rickandmortyapp.domain.models

import com.example.rickandmortyapp.domain.models.character.Location
import com.example.rickandmortyapp.domain.models.character.Origin

sealed class RickAndMorty {

    data class CharactersItem(
        val image: String?,
        val gender: String?,
        val species: String?,
        val created: String?,
        val origin: Origin,
        val name: String?,
        val location: Location,
        val episode: List<String>?,
        val id: Int?,
        val type: String?,
        val url: String?,
        val status: String?
    ) : RickAndMorty()

    data class EpisodesItem(
        val airDate: String?,
        val characters: List<String>?,
        val created: String?,
        val episode: String?,
        val name: String?,
        val id: Int?,
        val url: String?
    ) : RickAndMorty()

    data class LocationsItem(
        val created: String?,
        val name: String?,
        val residents: List<String>?,
        val id: Int?,
        val type: String?,
        val dimension: String?,
        val url: String?
    ) : RickAndMorty()
}