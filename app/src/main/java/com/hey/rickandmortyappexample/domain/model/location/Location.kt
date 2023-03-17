package com.hey.rickandmortyappexample.domain.model.location

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    var id : Int = 0,
    var name : String = "",
    var type : String = "",
    var dimension : String = "",
    var residents: MutableList<String> = mutableListOf(),
    var url : String = "",
    var created : String = ""
): Parcelable
