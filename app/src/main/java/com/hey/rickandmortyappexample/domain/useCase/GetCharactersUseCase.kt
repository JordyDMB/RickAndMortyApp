package com.hey.rickandmortyappexample.domain.useCase

import android.content.Context
import android.graphics.Bitmap
import com.hey.rickandmortyappexample.data.repository.CharacterRepository
import com.hey.rickandmortyappexample.domain.model.character.Character

class GetCharactersUseCase(private val context: Context) {

    suspend operator fun invoke() : MutableList<Character> {
        val response = CharacterRepository.getCharactersFromLocal()
        if (response.size>0){
            return response
        }
        return CharacterRepository.getCharactersFromServer(context)
    }

}


class GetCharacterImageUseCase(private val context: Context, private val character: Character){

    suspend operator fun invoke():Bitmap{
        var bitmap = CharacterRepository.getImageCharacterFromLocal(character.id, context)
        if (bitmap==null){
            bitmap = CharacterRepository.getImageCharacterFromApi(character.image, context)
        }
        return bitmap
    }

}

