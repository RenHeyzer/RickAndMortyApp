package com.example.rickandmortyapp.data.implementation

import com.example.rickandmortyapp.base.BaseRepository
import com.example.rickandmortyapp.data.implementation.pagingsources.EpisodesPagingSource
import com.example.rickandmortyapp.data.network.apiservices.EpisodesApiService
import com.example.rickandmortyapp.domain.repositories.EpisodesRepository
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val service: EpisodesApiService
) : BaseRepository(), EpisodesRepository {

    override fun getEpisodes(name: String) = doPagingRequest(EpisodesPagingSource(service, name))
}