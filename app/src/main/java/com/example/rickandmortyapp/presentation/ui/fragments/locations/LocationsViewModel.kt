package com.example.rickandmortyapp.presentation.ui.fragments.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.rickandmortyapp.base.BaseViewModel
import com.example.rickandmortyapp.domain.models.RickAndMorty
import com.example.rickandmortyapp.domain.usecases.GetLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val getAllLocations: GetLocationsUseCase
) : BaseViewModel() {

    private val _locationsState = MutableLiveData<PagingData<RickAndMorty.LocationsItem>>()
    val locationsState: LiveData<PagingData<RickAndMorty.LocationsItem>> = _locationsState

    fun getLocations(name: String) = viewModelScope.launch {
        getAllLocations(name).collect {
            _locationsState.value = it as PagingData<RickAndMorty.LocationsItem>
        }
    }
}