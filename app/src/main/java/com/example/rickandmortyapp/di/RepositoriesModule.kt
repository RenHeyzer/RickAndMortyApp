package com.example.rickandmortyapp.di

import com.example.rickandmortyapp.data.implementation.CharactersRepositoryImpl
import com.example.rickandmortyapp.data.implementation.EpisodesRepositoryImpl
import com.example.rickandmortyapp.data.implementation.LocationsRepositoryImpl
import com.example.rickandmortyapp.data.network.apiservices.CharactersApiService
import com.example.rickandmortyapp.data.network.apiservices.EpisodesApiService
import com.example.rickandmortyapp.data.network.apiservices.LocationsApiService
import com.example.rickandmortyapp.domain.repositories.CharactersRepository
import com.example.rickandmortyapp.domain.repositories.EpisodesRepository
import com.example.rickandmortyapp.domain.repositories.LocationsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    fun provideCharacterRepository(service: CharactersApiService): CharactersRepository =
        CharactersRepositoryImpl(service)

    @Provides
    fun provideLocationsRepository(service: LocationsApiService): LocationsRepository =
        LocationsRepositoryImpl(service)

    @Provides
    fun provideEpisodesRepository(service: EpisodesApiService): EpisodesRepository =
        EpisodesRepositoryImpl(service)
}