package com.example.rickandmortyapp.domain.models.location

import com.example.rickandmortyapp.base.IBaseDiffModel

data class LocationsModel(
    val created: String?,
    val name: String?,
    val residents: List<String>?,
    override val id: Int?,
    val type: String?,
    val dimension: String?,
    val url: String?
) : IBaseDiffModel