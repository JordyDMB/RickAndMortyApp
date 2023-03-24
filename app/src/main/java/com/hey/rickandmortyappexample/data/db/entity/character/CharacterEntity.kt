package com.hey.rickandmortyappexample.data.db.entity.character

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hey.rickandmortyappexample.domain.model.character.Character

@Entity(tableName = "character_entity_table")
data class CharacterEntity(
    @PrimaryKey
    var id : Int = 0,

    var name : String = "",
    var status : String = "",
    var species : String = "",
    var type : String = "",
    var gender : String = "",
    @Embedded
    var origin : OriginEntity,
    @Embedded
    var location : LocationMinEntity,
    var image : String = "",
    var episode : MutableList<String> =  mutableListOf(),
    var url : String = "",
    var created : String = "",
)


fun Character.toEntity() = CharacterEntity(id, name, status, species, type, gender,
    origin.toEntity(), location.toEntity(), image,
    episode, url, created)

