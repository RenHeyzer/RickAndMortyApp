package com.example.rickandmortyapp.data.network.dtos.models

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(

    @SerializedName("results")
    val results: ArrayList<T>,

    @SerializedName("info")
    val info: Info
)