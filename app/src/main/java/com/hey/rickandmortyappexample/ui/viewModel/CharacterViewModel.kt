package com.hey.rickandmortyappexample.ui.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hey.rickandmortyappexample.domain.model.character.Character
import com.hey.rickandmortyappexample.domain.useCase.GetCharactersUseCase
import kotlinx.coroutines.launch

class CharacterViewModel() : ViewModel() {

    val characterModel = MutableLiveData<MutableList<Character>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCrete(context: Context){
        viewModelScope.launch{
            isLoading.postValue(true)
            val getCharactersUseCase = GetCharactersUseCase(context)
            val result = getCharactersUseCase()
            characterModel.postValue(result)
            isLoading.postValue(false)
        }
    }

}