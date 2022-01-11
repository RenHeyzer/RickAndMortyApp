package com.example.rickandmortyapp.domain.usecases

import com.example.rickandmortyapp.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetCharactersBySearchUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    suspend operator fun invoke(name: String) = repository.getCharactersBySearch(name)
}