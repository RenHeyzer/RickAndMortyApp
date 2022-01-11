package com.example.rickandmortyapp.domain.repositories

import com.example.rickandmortyapp.common.resource.Resource
import com.example.rickandmortyapp.domain.models.PagingList
import com.example.rickandmortyapp.domain.models.RickAndMorty
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getCharacters(
        name: String,
        status: String,
        gender: String
    ): Flow<PagingList<RickAndMorty.CharactersItem>>

    suspend fun getCharactersBySearch(name: String): Flow<Resource<List<RickAndMorty.GeneralItem>>>

    fun getCharacterById(id: Int): Flow<Resource<RickAndMorty.CharactersItem>>
}