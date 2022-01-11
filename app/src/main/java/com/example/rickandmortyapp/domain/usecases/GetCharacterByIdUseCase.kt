package com.example.rickandmortyapp.domain.usecases

import com.example.rickandmortyapp.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    operator fun invoke(id: Int) = repository.getCharacterById(id)
}