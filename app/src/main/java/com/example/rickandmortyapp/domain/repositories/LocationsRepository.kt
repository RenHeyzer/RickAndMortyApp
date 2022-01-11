package com.example.rickandmortyapp.domain.repositories

import com.example.rickandmortyapp.common.resource.Resource
import com.example.rickandmortyapp.domain.models.PagingList
import com.example.rickandmortyapp.domain.models.RickAndMorty
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {

    fun getLocations(
        name: String,
        type: String,
        dimension: String
    ): Flow<PagingList<RickAndMorty.LocationsItem>>

    suspend fun getLocationsBySearch(name: String): Flow<Resource<List<RickAndMorty.GeneralItem>>>

    fun getLocationById(id: Int): Flow<Resource<RickAndMorty.LocationsItem>>
}