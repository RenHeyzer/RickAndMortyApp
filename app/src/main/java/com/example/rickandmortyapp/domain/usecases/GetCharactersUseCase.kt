package com.example.rickandmortyapp.domain.usecases

import com.example.rickandmortyapp.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    operator fun invoke(name: String) = repository.getCharacters(name)
}