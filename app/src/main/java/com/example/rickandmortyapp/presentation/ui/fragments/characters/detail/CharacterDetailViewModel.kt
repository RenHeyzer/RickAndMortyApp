package com.example.rickandmortyapp.presentation.ui.fragments.characters.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.base.BaseViewModel
import com.example.rickandmortyapp.domain.models.RickAndMorty
import com.example.rickandmortyapp.domain.usecases.GetCharacterByIdUseCase
import com.example.rickandmortyapp.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterId: GetCharacterByIdUseCase
) : BaseViewModel() {

    private val _characterDetailState = MutableLiveData<UIState<RickAndMorty.CharactersItem>>()
    val characterDetailState: LiveData<UIState<RickAndMorty.CharactersItem>> = _characterDetailState

    fun getCharacterById(id: Int) = viewModelScope.launch {
        subscribeTo(_characterDetailState) {
            getCharacterId(id)
        }
    }
}