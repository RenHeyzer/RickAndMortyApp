package com.example.rickandmortyapp.data.network.apiservices

import com.example.rickandmortyapp.data.network.dtos.models.ApiResponse
import com.example.rickandmortyapp.data.network.dtos.models.RickAndMortyDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodesApiService {

    @GET("api/episode")
    suspend fun getEpisodes(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("episode") episode: String
    ): ApiResponse<RickAndMortyDto.EpisodesItem>

    @GET("api/episode/{id}")
    suspend fun getEpisodeById(
        @Path("id") id: Int
    ): RickAndMortyDto.EpisodesItem
}