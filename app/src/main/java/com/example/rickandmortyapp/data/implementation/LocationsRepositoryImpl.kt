package com.example.rickandmortyapp.data.implementation

import com.example.rickandmortyapp.base.BaseRepository
import com.example.rickandmortyapp.data.implementation.pagingsources.LocationsPagingSource
import com.example.rickandmortyapp.data.network.apiservices.LocationsApiService
import com.example.rickandmortyapp.domain.repositories.LocationsRepository
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val service: LocationsApiService
) : BaseRepository(), LocationsRepository {

    override fun getLocations(name: String) = doPagingRequest(LocationsPagingSource(service, name))
}