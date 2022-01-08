package com.example.rickandmortyapp.domain.models.episode

import com.example.rickandmortyapp.base.IBaseDiffModel

data class EpisodesModel(
    val airDate: String?,
    val characters: List<String>?,
    val created: String?,
    val episode: String?,
    val name: String?,
    override val id: Int?,
    val url: String?
) : IBaseDiffModel