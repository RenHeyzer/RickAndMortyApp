package com.example.rickandmortyapp.presentation.ui.fragments.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.rickandmortyapp.base.BaseViewModel
import com.example.rickandmortyapp.domain.models.RickAndMorty
import com.example.rickandmortyapp.domain.usecases.GetEpisodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val getAllEpisodes: GetEpisodesUseCase
) : BaseViewModel() {

    private val _episodesState = MutableLiveData<PagingData<RickAndMorty.EpisodesItem>>()
    val episodesSate: LiveData<PagingData<RickAndMorty.EpisodesItem>> = _episodesState

    private val _episodesFilterState = MutableLiveData<PagingData<RickAndMorty.EpisodesItem>>()
    val episodesFilterSate: LiveData<PagingData<RickAndMorty.EpisodesItem>> = _episodesFilterState

    fun getEpisodes(name: String) {
        viewModelScope.launch {
            getAllEpisodes(name, episode = "").collect {
                _episodesState.value = it as PagingData<RickAndMorty.EpisodesItem>
            }
        }
    }

    fun getEpisodesWithFilter(name: String, episode: String) {
        viewModelScope.launch {
            getAllEpisodes(name, episode).collect {
                _episodesFilterState.value = it as PagingData<RickAndMorty.EpisodesItem>
            }
        }
    }
}