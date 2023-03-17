package com.hey.rickandmortyappexample.ui.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hey.rickandmortyappexample.domain.model.character.Character
import com.hey.rickandmortyappexample.domain.model.location.Location
import com.hey.rickandmortyappexample.domain.useCase.GetCharactersUseCase
import com.hey.rickandmortyappexample.domain.useCase.GetLocationsUseCase
import kotlinx.coroutines.launch

class LocationViewModel() : ViewModel() {

    val locationModel = MutableLiveData<MutableList<Location>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCrete(context: Context){
        viewModelScope.launch{
            isLoading.postValue(true)
            val useCase = GetLocationsUseCase(context)
            val result = useCase()
            locationModel.postValue(result)
            isLoading.postValue(false)
        }
    }

}