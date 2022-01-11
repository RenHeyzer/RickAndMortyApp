package com.example.rickandmortyapp.domain.usecases

import com.example.rickandmortyapp.domain.repositories.EpisodesRepository
import javax.inject.Inject

class GetEpisodeByIdUseCase @Inject constructor(
    private val repository: EpisodesRepository
) {

    operator fun invoke(id: Int) = repository.getEpisodeById(id)
}