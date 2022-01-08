package com.example.rickandmortyapp.data.network.dtos.models.location

import com.example.rickandmortyapp.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

data class LocationsModelDto(

    @SerializedName("created")
    val created: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("residents")
    val residents: List<String>?,

    @SerializedName("id")
    override val id: Int?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("dimension")
    val dimension: String?,

    @SerializedName("url")
    val url: String?
) : IBaseDiffModel