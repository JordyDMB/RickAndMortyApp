package com.hey.rickandmortyappexample.data.network.service

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.gson.Gson
import com.hey.rickandmortyappexample.data.model.ApiResponse
import com.hey.rickandmortyappexample.data.network.RetrofitProvider
import com.hey.rickandmortyappexample.data.network.interfaces.CharacterApiInterface
import com.hey.rickandmortyappexample.domain.model.character.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

object CharacterApiService {

    suspend fun getCharacters() : ApiResponse<MutableList<Character>>{
        return withContext(Dispatchers.IO){

            val list = mutableListOf<Character>()
            lateinit var apiResponse : ApiResponse<MutableList<Character>>

           runCatching {
                val r  = RetrofitProvider.getRetrofit()
                    .create(CharacterApiInterface::class.java)
                    .getCharacters()

                r.body()?.let {
                    val response = JSONObject(it.string())
                    val results = response.getJSONArray("results")
                    for ( i in 0 until results.length()){
                        val character = Gson().fromJson(results.getJSONObject(i).toString(), Character::class.java)
                        list.add(character)
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


    suspend fun getImageCharacter(url : String) : ApiResponse<Bitmap>{
        return withContext(Dispatchers.IO){

            lateinit var apiResponse : ApiResponse<Bitmap>
            lateinit var bitmap: Bitmap

           runCatching {
                val r  = RetrofitProvider.getRetrofit()
                    .create(CharacterApiInterface::class.java)
                    .getImageCharacter(url)

                r.body()?.let {
                    bitmap = BitmapFactory.decodeStream(it.byteStream())
                }
                bitmap
            }.onFailure {
                apiResponse = ApiResponse.Throw(it)
            }.onSuccess {
                apiResponse = ApiResponse.Data(bitmap)
            }
            apiResponse
        }
    }
}