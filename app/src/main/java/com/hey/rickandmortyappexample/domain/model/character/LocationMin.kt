package com.hey.rickandmortyappexample.domain.model.character

import android.os.Parcelable
import com.hey.rickandmortyappexample.data.db.entity.character.LocationMinEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationMin(
    var name : String = "",
    var url : String = ""
):Parcelable

fun LocationMinEntity.toDomain() = LocationMin(name, url)