package com.example.rickandmortyapp.data.implementation.pagingsources

import com.example.rickandmortyapp.base.BasePagingSource
import com.example.rickandmortyapp.data.network.apiservices.CharactersApiService
import com.example.rickandmortyapp.data.network.dtos.models.RickAndMortyDto
import com.example.rickandmortyapp.data.network.dtos.models.toCharacters
import com.example.rickandmortyapp.domain.models.RickAndMorty

class CharactersPagingSource(
    private val service: CharactersApiService,
    private val name: String
) : BasePagingSource<RickAndMortyDto.CharactersItem, RickAndMorty.CharactersItem>(
    { service.getCharacters(it, name) },
    { data -> data.map { it.toCharacters() } }
)