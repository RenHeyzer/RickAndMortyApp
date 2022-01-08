package com.example.rickandmortyapp.data.implementation.pagingsources

import com.example.rickandmortyapp.base.BasePagingSource
import com.example.rickandmortyapp.data.network.apiservices.LocationsApiService
import com.example.rickandmortyapp.data.network.dtos.models.RickAndMortyDto
import com.example.rickandmortyapp.data.network.dtos.models.toLocations
import com.example.rickandmortyapp.domain.models.RickAndMorty

class LocationsPagingSource(
    private val service: LocationsApiService,
    private val name: String
) :
    BasePagingSource<RickAndMortyDto.LocationsItem, RickAndMorty.LocationsItem>(
        { service.getLocations(it, name) },
        { data -> data.map { it.toLocations() } }
    )