package com.hey.rickandmortyappexample.data.db.dao

import com.hey.rickandmortyappexample.domain.model.character.Character

interface CharacterDao {


    suspend fun getAllCharacter() : MutableList<Character>
}