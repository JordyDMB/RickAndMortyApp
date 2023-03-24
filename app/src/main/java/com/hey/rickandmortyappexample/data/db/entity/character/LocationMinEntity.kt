package com.hey.rickandmortyappexample.data.db.entity.character

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hey.rickandmortyappexample.domain.model.character.LocationMin

@Entity(tableName = "locationmin_entity_table")
data class LocationMinEntity (
    @PrimaryKey
    @ColumnInfo("name_location")
    var name : String = "",
    @ColumnInfo("url_location")
    var url : String = ""
)


fun LocationMin.toEntity() = LocationMinEntity(name, url)