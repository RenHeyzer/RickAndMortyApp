package com.example.rickandmortyapp.domain.usecases

import com.example.rickandmortyapp.domain.repositories.LocationsRepository
import javax.inject.Inject

class GetLocationByIdUseCase @Inject constructor(
    private val repository: LocationsRepository
) {

    operator fun invoke(id: Int) = repository.getLocationById(id)
}