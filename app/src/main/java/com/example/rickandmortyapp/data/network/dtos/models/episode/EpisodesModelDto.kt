package com.example.rickandmortyapp.data.network.dtos.models.episode

import com.example.rickandmortyapp.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

data class EpisodesModelDto(

    @SerializedName("air_date")
    val airDate: String?,

    @SerializedName("characters")
    val characters: List<String>?,

    @SerializedName("created")
    val created: String?,

    @SerializedName("episode")
    val episode: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("id")
    override val id: Int?,

    @SerializedName("url")
    val url: String?
) : IBaseDiffModel