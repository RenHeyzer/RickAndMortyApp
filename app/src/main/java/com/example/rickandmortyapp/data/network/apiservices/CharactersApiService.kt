package com.example.rickandmortyapp.data.network.apiservices

import com.example.rickandmortyapp.data.network.dtos.models.ApiResponse
import com.example.rickandmortyapp.data.network.dtos.models.RickAndMortyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApiService {

    @GET("api/character")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("name") name: String
    ): ApiResponse<RickAndMortyDto.CharactersItem>
}