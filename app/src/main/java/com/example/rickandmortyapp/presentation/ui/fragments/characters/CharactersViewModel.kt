package com.example.rickandmortyapp.presentation.ui.fragments.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.rickandmortyapp.base.BaseViewModel
import com.example.rickandmortyapp.domain.models.RickAndMorty
import com.example.rickandmortyapp.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharacters: GetCharactersUseCase
) : BaseViewModel() {

    private val _charactersState = MutableLiveData<PagingData<RickAndMorty.CharactersItem>>()
    val charactersState: LiveData<PagingData<RickAndMorty.CharactersItem>> = _charactersState

    fun getCharacters(name: String) = viewModelScope.launch {
        getAllCharacters(name).collect {
            _charactersState.value = it as PagingData<RickAndMorty.CharactersItem>
        }
    }
}