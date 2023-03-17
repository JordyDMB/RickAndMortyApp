package com.hey.rickandmortyappexample.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {

    var ip_main = "https://rickandmortyapi.com"

    fun getRetrofit(): Retrofit{
       return Retrofit.Builder()
           .baseUrl(ip_main)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
    }

}