package com.example.rickandmortyapp.data.implementation

import com.example.rickandmortyapp.base.BaseRepository
import com.example.rickandmortyapp.common.resource.Resource
import com.example.rickandmortyapp.data.implementation.pagingsources.EpisodesPagingSource
import com.example.rickandmortyapp.data.network.apiservices.EpisodesApiService
import com.example.rickandmortyapp.data.network.dtos.models.toEpisodes
import com.example.rickandmortyapp.data.network.dtos.models.toGeneral
import com.example.rickandmortyapp.domain.models.RickAndMorty
import com.example.rickandmortyapp.domain.repositories.EpisodesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val service: EpisodesApiService
) : BaseRepository(), EpisodesRepository {

    override fun getEpisodes(name: String, episode: String) =
        doPagingRequest(EpisodesPagingSource(service, name, episode))

    override suspend fun getEpisodesBySearch(name: String) = doRequest {
        service.getEpisodes(1, name, "").results.map {
            it.toGeneral()
        }
    }

    override fun getEpisodeById(id: Int) = doRequest {
        service.getEpisodeById(id).toEpisodes()
    }
}