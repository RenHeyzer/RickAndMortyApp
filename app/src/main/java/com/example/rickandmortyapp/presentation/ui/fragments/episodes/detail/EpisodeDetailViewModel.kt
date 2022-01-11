package com.example.rickandmortyapp.presentation.ui.fragments.episodes.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.base.BaseViewModel
import com.example.rickandmortyapp.domain.models.RickAndMorty
import com.example.rickandmortyapp.domain.usecases.GetEpisodeByIdUseCase
import com.example.rickandmortyapp.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val getEpisodeId: GetEpisodeByIdUseCase
) : BaseViewModel() {

    private val _episodeDetailState = MutableLiveData<UIState<RickAndMorty.EpisodesItem>>()
    val episodeDetailState: LiveData<UIState<RickAndMorty.EpisodesItem>> = _episodeDetailState

    fun getEpisodeById(id: Int) = viewModelScope.launch {
        subscribeTo(_episodeDetailState) {
            getEpisodeId(id)
        }
    }
}