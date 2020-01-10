package com.trunghtluu.moviedexkotlin.network

import com.trunghtluu.moviedexkotlin.model.MovieSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/3/search/movie")
    abstract fun getSearch(
        @Query("api_key") api_key: String,
        @Query("query") query: String
    ): Call<MovieSearch>

}