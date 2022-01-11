package com.example.rickandmortyapp.domain.usecases

import com.example.rickandmortyapp.domain.repositories.EpisodesRepository
import javax.inject.Inject

class GetEpisodesBySearchUseCase @Inject constructor(
    private val repository: EpisodesRepository
) {

    suspend operator fun invoke(name: String) = repository.getEpisodesBySearch(name)
}