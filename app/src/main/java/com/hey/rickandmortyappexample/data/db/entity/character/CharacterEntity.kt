package com.hey.rickandmortyappexample.data.db.entity.character

import androidx.room.Entity

@Entity(tableName = "quote_table")
data class CharacterEntity(
    var id : Int = 0,
    var name : String = "",
    var status : String = "",
    var species : String = "",
    var type : String = "",
    var gender : String = "",
    var origin : OriginEntity?,
    var location : LocationMinEntity?,
    var image : String = "",
    var episode : MutableList<String> =  mutableListOf(),
    var url : String = "",
    var created : String = "",
    var cacheImage: String = ""
)
