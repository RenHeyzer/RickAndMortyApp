package com.example.rickandmortyapp.domain.repositories

import com.example.rickandmortyapp.domain.models.PagingList
import com.example.rickandmortyapp.domain.models.RickAndMorty
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {

    fun getEpisodes(name: String): Flow<PagingList<RickAndMorty.EpisodesItem>>
}