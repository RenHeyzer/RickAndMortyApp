package com.example.rickandmortyapp.data.network.dtos.models.character

import com.example.rickandmortyapp.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

data class CharactersModelDto(

    @SerializedName("image")
    val image: String?,

    @SerializedName("gender")
    val gender: String?,

    @SerializedName("species")
    val species: String?,

    @SerializedName("created")
    val created: String?,

    @SerializedName("origin")
    val origin: OriginDto,

    @SerializedName("name")
    val name: String?,

    @SerializedName("location")
    val location: LocationDto,

    @SerializedName("episode")
    val episode: List<String>?,

    @SerializedName("id")
    override val id: Int?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("status")
    val status: String?
) : IBaseDiffModel
