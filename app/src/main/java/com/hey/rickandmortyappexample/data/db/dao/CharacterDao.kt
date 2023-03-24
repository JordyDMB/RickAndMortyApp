package com.hey.rickandmortyappexample.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hey.rickandmortyappexample.data.db.entity.character.CharacterEntity
import com.hey.rickandmortyappexample.domain.model.character.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_entity_table")
    suspend fun getAllCharacter() : MutableList<CharacterEntity>

    @Query("SELECT * FROM character_entity_table WHERE id=:mid")
    suspend fun getCharacterById(mid : Int) : CharacterEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacter(list: MutableList<CharacterEntity>)

}