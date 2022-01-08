package com.example.rickandmortyapp.data.network.dtos.models

import com.example.rickandmortyapp.data.network.dtos.models.character.CharactersModelDto
import com.example.rickandmortyapp.data.network.dtos.models.character.LocationDto
import com.example.rickandmortyapp.data.network.dtos.models.character.OriginDto
import com.example.rickandmortyapp.data.network.dtos.models.episode.EpisodesModelDto
import com.example.rickandmortyapp.data.network.dtos.models.location.LocationsModelDto
import com.example.rickandmortyapp.domain.models.RickAndMorty
import com.example.rickandmortyapp.domain.models.character.CharactersModel
import com.example.rickandmortyapp.domain.models.character.Location
import com.example.rickandmortyapp.domain.models.character.Origin
import com.example.rickandmortyapp.domain.models.episode.EpisodesModel
import com.example.rickandmortyapp.domain.models.location.LocationsModel

fun RickAndMortyDto.CharactersItem.toCharacters(): RickAndMorty.CharactersItem =
    RickAndMorty.CharactersItem(
        image,
        gender,
        species,
        created,
        origin.toOrigin(),
        name,
        location.toLocation(),
        episode,
        id,
        type,
        url,
        status
    )

fun CharactersModelDto.toCharactersModel(): CharactersModel =
    CharactersModel(
        image,
        gender,
        species,
        created,
        origin.toOrigin(),
        name,
        location.toLocation(),
        episode,
        id,
        type,
        url,
        status
    )

fun OriginDto.toOrigin(): Origin =
    Origin(name, url)

fun LocationDto.toLocation(): Location =
    Location(name, url)

fun RickAndMortyDto.LocationsItem.toLocations(): RickAndMorty.LocationsItem =
    RickAndMorty.LocationsItem(
        created,
        name,
        residents,
        id,
        type,
        dimension,
        url
    )

fun LocationsModelDto.toLocationsModel(): LocationsModel =
    LocationsModel(
        created,
        name,
        residents,
        id,
        type,
        dimension,
        url
    )

fun RickAndMortyDto.EpisodesItem.toEpisodes(): RickAndMorty.EpisodesItem =
    RickAndMorty.EpisodesItem(
        airDate,
        characters,
        created,
        episode,
        name,
        id,
        url
    )

fun EpisodesModelDto.toEpisodesModel(): EpisodesModel =
    EpisodesModel(
        airDate,
        characters,
        created,
        episode,
        name,
        id,
        url
    )