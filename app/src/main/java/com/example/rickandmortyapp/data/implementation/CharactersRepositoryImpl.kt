package com.example.rickandmortyapp.data.implementation

import com.example.rickandmortyapp.base.BaseRepository
import com.example.rickandmortyapp.data.implementation.pagingsources.CharactersPagingSource
import com.example.rickandmortyapp.data.network.apiservices.CharactersApiService
import com.example.rickandmortyapp.data.network.dtos.models.toCharacters
import com.example.rickandmortyapp.data.network.dtos.models.toGeneral
import com.example.rickandmortyapp.domain.repositories.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val service: CharactersApiService
) : BaseRepository(), CharactersRepository {

    override fun getCharacters(name: String, status: String, gender: String) =
        doPagingRequest(CharactersPagingSource(service, name, status, gender))

    override suspend fun getCharactersBySearch(name: String) = doRequest {
        service.getCharacters(1, name, "", "").results.map {
            it.toGeneral()
        }
    }

    override fun getCharacterById(id: Int) = doRequest {
        service.getCharacterById(id).toCharacters()
    }
}