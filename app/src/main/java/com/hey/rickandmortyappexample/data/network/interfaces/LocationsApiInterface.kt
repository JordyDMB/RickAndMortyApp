package com.hey.rickandmortyappexample.data.network.interfaces

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface LocationsApiInterface {

    @GET("/api/location")
    suspend fun getLocations() : Response<ResponseBody>
}