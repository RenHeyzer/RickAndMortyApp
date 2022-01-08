package com.example.rickandmortyapp.data.implementation

import com.example.rickandmortyapp.base.BaseRepository
import com.example.rickandmortyapp.data.implementation.pagingsources.CharactersPagingSource
import com.example.rickandmortyapp.data.network.apiservices.CharactersApiService
import com.example.rickandmortyapp.domain.repositories.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val service: CharactersApiService
) : BaseRepository(), CharactersRepository {

    override fun getCharacters(name: String) =
        doPagingRequest(CharactersPagingSource(service, name))
}