package com.example.rickandmortyapp.data.implementation

import com.example.rickandmortyapp.base.BaseRepository
import com.example.rickandmortyapp.data.implementation.pagingsources.LocationsPagingSource
import com.example.rickandmortyapp.data.network.apiservices.LocationsApiService
import com.example.rickandmortyapp.data.network.dtos.models.toGeneral
import com.example.rickandmortyapp.data.network.dtos.models.toLocations
import com.example.rickandmortyapp.domain.repositories.LocationsRepository
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val service: LocationsApiService
) : BaseRepository(), LocationsRepository {

    override fun getLocations(name: String, type: String, dimension: String) =
        doPagingRequest(LocationsPagingSource(service, name, type, dimension))

    override suspend fun getLocationsBySearch(name: String) = doRequest {
        service.getLocations(1, name, "", "").results.map {
            it.toGeneral()
        }
    }

    override fun getLocationById(id: Int) = doRequest {
        service.getLocationById(id).toLocations()
    }
}