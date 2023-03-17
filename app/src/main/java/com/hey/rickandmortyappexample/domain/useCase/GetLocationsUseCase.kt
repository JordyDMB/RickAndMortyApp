package com.hey.rickandmortyappexample.domain.useCase

import android.content.Context
import android.graphics.Bitmap
import com.hey.rickandmortyappexample.data.repository.CharacterRepository
import com.hey.rickandmortyappexample.data.repository.LocationRepository
import com.hey.rickandmortyappexample.domain.model.character.Character
import com.hey.rickandmortyappexample.domain.model.location.Location

class GetLocationsUseCase(private val context: Context) {

    suspend operator fun invoke() : MutableList<Location>{
        val response = LocationRepository.getLocationsFromServer(context)
        if (response.size>0){
            return response
        }
        return LocationRepository.getLocationsFromLocal()
    }

}


