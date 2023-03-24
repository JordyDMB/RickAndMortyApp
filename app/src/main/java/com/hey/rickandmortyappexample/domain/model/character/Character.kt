package com.hey.rickandmortyappexample.domain.model.character

import android.content.Context
import android.graphics.Bitmap
import android.os.Parcelable
import com.hey.rickandmortyappexample.data.db.entity.character.CharacterEntity
import com.hey.rickandmortyappexample.domain.useCase.GetCharacterImageUseCase
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    var id : Int = 0,
    var name : String = "",
    var status : String = "",
    var species : String = "",
    var type : String = "",
    var gender : String = "",
    var origin : Origin,
    var location : LocationMin,
    var image : String = "",
    var episode : MutableList<String> =  mutableListOf(),
    var url : String = "",
    var created : String = "",
    var bitmap: Bitmap?,
    var scaledBitmap: Bitmap?,
):Parcelable{
    suspend fun getImageByUrl(context: Context) : Bitmap?{
        val bitmap = GetCharacterImageUseCase(context, this)
        this.bitmap = bitmap()
        return this.bitmap
    }
}

fun CharacterEntity.toDomain() = Character(id, name, status, species,
    type, gender, origin.toDomain(), location.toDomain(),
    image, episode, url, created, null, null)
