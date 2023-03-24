package com.hey.rickandmortyappexample.data.repository

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import com.hey.rickandmortyappexample.R
import com.hey.rickandmortyappexample.RickAndMortyApp
import com.hey.rickandmortyappexample.data.db.dao.CharacterDao
import com.hey.rickandmortyappexample.data.db.entity.character.CharacterEntity
import com.hey.rickandmortyappexample.data.db.entity.character.toEntity
import com.hey.rickandmortyappexample.data.model.ApiResponse
import com.hey.rickandmortyappexample.data.network.service.CharacterApiService
import com.hey.rickandmortyappexample.domain.model.character.Character
import com.hey.rickandmortyappexample.domain.model.character.toDomain

object CharacterRepository {

    suspend fun getCharactersFromServer(context: Context):MutableList<Character>{
        return when (val response = CharacterApiService.getCharacters()){
            is ApiResponse.Data -> {
                insertCharacter(context, response.data.map { it.toEntity() }.toMutableList())
                response.data
            }
            is ApiResponse.Throw -> {
                Toast.makeText(context, response.throwable.localizedMessage, Toast.LENGTH_LONG).show()
                mutableListOf()
            }
        }
    }

    suspend fun getCharactersFromLocal(context: Context) : MutableList<Character> {
        getCharacterDao(context)?.let { dao->
            return dao.getAllCharacter().map { it.toDomain() }.toMutableList()
        }
        return mutableListOf()
    }

    suspend fun getImageCharacterFromApi(url : String, context: Context): Bitmap{
        return when (val response = CharacterApiService.getImageCharacter(url)){
            is ApiResponse.Data -> {
                response.data
            }
            is ApiResponse.Throw ->{
                AppCompatResources.getDrawable(context, R.drawable.ic_error_outline_24)!!.toBitmap()
            }
        }
    }

    suspend fun getImageCharacterFromLocal(id : Int, context: Context) : Bitmap?{
        var bitmap: Bitmap? = null
        runCatching {
            AppCompatResources.getDrawable(context, id)?.toBitmap()
        }.onFailure {
            bitmap = null
        }.onSuccess {
            bitmap = it
        }
        return bitmap
    }

    suspend fun insertCharacter(context: Context, list: MutableList<CharacterEntity>){
        getCharacterDao(context).insertAllCharacter(list)
    }

    private fun getCharacterDao(context: Context) : CharacterDao {
        return ((context as Activity).application as RickAndMortyApp).database.characterDao()
    }

}