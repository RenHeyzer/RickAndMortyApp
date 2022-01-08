package com.example.rickandmortyapp.presentation.ui.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.rickandmortyapp.base.BaseViewModel
import com.example.rickandmortyapp.domain.models.RickAndMorty
import com.example.rickandmortyapp.domain.usecases.GetCharactersUseCase
import com.example.rickandmortyapp.domain.usecases.GetEpisodesUseCase
import com.example.rickandmortyapp.domain.usecases.GetLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAllCharacters: GetCharactersUseCase,
    private val getAllLocations: GetLocationsUseCase,
    private val getAllEpisodes: GetEpisodesUseCase
) : BaseViewModel() {

    private val _allState = MutableLiveData<PagingData<RickAndMorty>>()
    val allState: LiveData<PagingData<RickAndMorty>> = _allState

    fun processAllRequest(name: String) = viewModelScope.launch {
        val charactersDeferred =
            withContext(Dispatchers.IO) {
                getAllCharacters(name)
            }
        val locationsDeferred =
            withContext(Dispatchers.IO) {
                getAllLocations(name)
            }
        val episodesDeferred =
            withContext(Dispatchers.IO) {
                getAllEpisodes(name)
            }

        charactersDeferred.collect {
            _allState.postValue(it as PagingData<RickAndMorty>)
        }

        locationsDeferred.collect {
            _allState.postValue(it as PagingData<RickAndMorty>)
        }

        episodesDeferred.collect {
            _allState.postValue(it as PagingData<RickAndMorty>)
        }
    }
}