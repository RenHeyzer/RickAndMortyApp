package com.example.rickandmortyapp.domain.repositories

import com.example.rickandmortyapp.common.resource.Resource
import com.example.rickandmortyapp.domain.models.PagingList
import com.example.rickandmortyapp.domain.models.RickAndMorty
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {

    fun getEpisodes(
        name: String,
        episode: String
    ): Flow<PagingList<RickAndMorty.EpisodesItem>>

    suspend fun getEpisodesBySearch(name: String): Flow<Resource<List<RickAndMorty.GeneralItem>>>

    fun getEpisodeById(id: Int): Flow<Resource<RickAndMorty.EpisodesItem>>
}