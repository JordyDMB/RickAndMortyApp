package com.hey.rickandmortyappexample.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import com.hey.rickandmortyappexample.R
import com.hey.rickandmortyappexample.data.model.ApiResponse
import com.hey.rickandmortyappexample.data.network.service.CharacterApiService
import com.hey.rickandmortyappexample.data.network.service.LocationsApiService
import com.hey.rickandmortyappexample.domain.model.character.Character
import com.hey.rickandmortyappexample.domain.model.location.Location

object LocationRepository {

    suspend fun getLocationsFromServer(context: Context):MutableList<Location>{
        return when (val response = LocationsApiService.getLocations()){
            is ApiResponse.Data -> {
                response.data
            }
            is ApiResponse.Throw -> {
                Toast.makeText(context, response.throwable.localizedMessage, Toast.LENGTH_LONG).show()
                mutableListOf()
            }
        }
    }

    suspend fun getLocationsFromLocal() : MutableList<Location>{
        return mutableListOf()
    }

}