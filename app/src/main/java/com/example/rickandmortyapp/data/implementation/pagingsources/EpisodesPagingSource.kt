package com.example.rickandmortyapp.data.implementation.pagingsources

import com.example.rickandmortyapp.base.BasePagingSource
import com.example.rickandmortyapp.data.network.apiservices.EpisodesApiService
import com.example.rickandmortyapp.data.network.dtos.models.RickAndMortyDto
import com.example.rickandmortyapp.data.network.dtos.models.toEpisodes
import com.example.rickandmortyapp.domain.models.RickAndMorty

class EpisodesPagingSource(
    private val service: EpisodesApiService,
    private val name: String
) :
    BasePagingSource<RickAndMortyDto.EpisodesItem, RickAndMorty.EpisodesItem>(
        { service.getEpisodes(it, name) },
        { data -> data.map { it.toEpisodes() } }
    )