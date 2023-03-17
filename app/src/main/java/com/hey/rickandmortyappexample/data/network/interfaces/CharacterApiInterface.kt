package com.hey.rickandmortyappexample.data.network.interfaces

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface CharacterApiInterface {

    @GET("/api/character")
    suspend fun getCharacters() : Response<ResponseBody>

    @GET
    suspend fun getImageCharacter(@Url url: String) : Response<ResponseBody>

}