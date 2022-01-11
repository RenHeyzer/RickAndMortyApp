package com.example.rickandmortyapp.presentation.ui.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.base.BaseViewModel
import com.example.rickandmortyapp.domain.models.RickAndMorty
import com.example.rickandmortyapp.domain.usecases.GetCharactersBySearchUseCase
import com.example.rickandmortyapp.domain.usecases.GetEpisodesBySearchUseCase
import com.example.rickandmortyapp.domain.usecases.GetLocationsBySearchUseCase
import com.example.rickandmortyapp.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAllCharactersBySearch: GetCharactersBySearchUseCase,
    private val getAllLocationsBySearch: GetLocationsBySearchUseCase,
    private val getAllEpisodesBySearch: GetEpisodesBySearchUseCase
) : BaseViewModel() {

    private val _generalState = MutableLiveData<UIState<List<RickAndMorty.GeneralItem>>>()
    val generalState: LiveData<UIState<List<RickAndMorty.GeneralItem>>> = _generalState

    fun processAllRequests(
        name: String
    ) {
        viewModelScope.launch {
            val charactersDeferred = async { getAllCharactersBySearch(name) }
            val locationsDeferred = async { getAllLocationsBySearch(name) }
            val episodesDeferred = async { getAllEpisodesBySearch(name) }

            val general = awaitAll(charactersDeferred, locationsDeferred, episodesDeferred)
            val generalList = ArrayList<RickAndMorty.GeneralItem>()

            general.map {
                subscribeToAsync(_generalState, generalList) {
                    it
                }
            }
        }
    }
}