package com.example.rickandmortyapp.domain.usecases

import com.example.rickandmortyapp.domain.repositories.LocationsRepository
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(
    private val repository: LocationsRepository
) {

    operator fun invoke(name: String, type: String, dimension: String) =
        repository.getLocations(name, type, dimension)
}