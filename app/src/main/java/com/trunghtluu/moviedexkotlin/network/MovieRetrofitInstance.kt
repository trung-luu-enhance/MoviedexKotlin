package com.trunghtluu.moviedexkotlin.network

import com.trunghtluu.moviedexkotlin.utils.Constants
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import com.trunghtluu.moviedexkotlin.model.MovieSearch
import retrofit2.Call


class MovieRetrofitInstance {

    private val movieService: MovieService

    init {
        movieService = createMovieService(getInstance());
    }

    private fun getInstance(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private fun createMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    fun getSearch(query: String): Call<MovieSearch> {
        return movieService.getSearch(Constants.API_KEY, query)
    }

}