package com.hey.rickandmortyappexample.data.db.entity.character

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hey.rickandmortyappexample.domain.model.character.Origin

@Entity(tableName = "origin_entity_table")
data class OriginEntity(
    @PrimaryKey
    @ColumnInfo("name_origin")
    var name : String = "",
    @ColumnInfo("url_origin")
    var url : String = ""
)

fun Origin.toEntity() = OriginEntity(name, url)