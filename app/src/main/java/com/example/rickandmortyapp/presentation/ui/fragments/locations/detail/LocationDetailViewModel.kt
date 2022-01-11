package com.example.rickandmortyapp.presentation.ui.fragments.locations.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.base.BaseViewModel
import com.example.rickandmortyapp.domain.models.RickAndMorty
import com.example.rickandmortyapp.domain.usecases.GetLocationByIdUseCase
import com.example.rickandmortyapp.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val getLocationId: GetLocationByIdUseCase
) : BaseViewModel() {

    private val _locationDetailState = MutableLiveData<UIState<RickAndMorty.LocationsItem>>()
    val locationDetailState: LiveData<UIState<RickAndMorty.LocationsItem>> = _locationDetailState

    fun getLocationById(id: Int) = viewModelScope.launch {
        subscribeTo(_locationDetailState) {
            getLocationId(id)
        }
    }
}