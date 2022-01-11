package com.example.rickandmortyapp.domain.usecases

import com.example.rickandmortyapp.domain.repositories.LocationsRepository
import javax.inject.Inject

class GetLocationsBySearchUseCase @Inject constructor(
    private val repository: LocationsRepository
) {

    suspend operator fun invoke(name: String) = repository.getLocationsBySearch(name)
}