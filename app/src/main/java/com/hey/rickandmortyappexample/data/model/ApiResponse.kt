package com.hey.rickandmortyappexample.data.model

sealed class ApiResponse <T> {
    class Data <T> (var data : T) : ApiResponse<T>()
    class Throw <T> (var throwable: Throwable) : ApiResponse<T>()
}
