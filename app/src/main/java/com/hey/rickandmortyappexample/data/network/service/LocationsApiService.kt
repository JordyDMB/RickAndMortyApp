package com.hey.rickandmortyappexample.data.network.service

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.gson.Gson
import com.hey.rickandmortyappexample.data.model.ApiResponse
import com.hey.rickandmortyappexample.data.network.RetrofitProvider
import com.hey.rickandmortyappexample.data.network.interfaces.CharacterApiInterface
import com.hey.rickandmortyappexample.data.network.interfaces.LocationsApiInterface
import com.hey.rickandmortyappexample.domain.model.character.Character
import com.hey.rickandmortyappexample.domain.model.location.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

object LocationsApiService {

    suspend fun getLocations() : ApiResponse<MutableList<Location>>{
        return withContext(Dispatchers.IO){

            val list = mutableListOf<Location>()
            lateinit var apiResponse : ApiResponse<MutableList<Location>>

           runCatching {
                val r  = RetrofitProvider.getRetrofit()
                    .create(LocationsApiInterface::class.java)
                    .getLocations()

                r.body()?.let {
                    val response = JSONObject(it.string())
                    val results = response.getJSONArray("results")
                    for ( i in 0 until results.length()){
                        val location = Gson().fromJson(results.getJSONObject(i).toString(), Location::class.java)
                        list.add(location)
                    }
                }
                list
            }.onFailure {
                apiResponse = ApiResponse.Throw(it)
            }.onSuccess {
                apiResponse = ApiResponse.Data(list)
            }
            apiResponse
        }
    }


}