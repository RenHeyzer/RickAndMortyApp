package com.example.rickandmortyapp.domain.repositories

import com.example.rickandmortyapp.domain.models.PagingList
import com.example.rickandmortyapp.domain.models.RickAndMorty
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getCharacters(name: String): Flow<PagingList<RickAndMorty.CharactersItem>>
}