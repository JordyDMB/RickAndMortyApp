package com.hey.rickandmortyappexample.domain.model.character

import android.os.Parcelable
import com.hey.rickandmortyappexample.data.db.entity.character.OriginEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Origin(
    var name : String = "",
    var url : String = ""
):Parcelable

fun OriginEntity.toDomain() = Origin(name, url)