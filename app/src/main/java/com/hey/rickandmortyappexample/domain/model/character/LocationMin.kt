package com.hey.rickandmortyappexample.domain.model.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationMin(
    var name : String = "",
    var url : String = ""
):Parcelable
