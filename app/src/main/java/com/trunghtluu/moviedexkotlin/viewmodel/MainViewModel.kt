package com.trunghtluu.moviedexkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.trunghtluu.moviedexkotlin.model.MovieSearch
import com.trunghtluu.moviedexkotlin.network.MovieRetrofitInstance
import android.util.Log
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val movieRetrofitInstance = MovieRetrofitInstance()
    private val resultLiveData = MutableLiveData<MovieSearch>()

    fun getSearches(query: String) {
        movieRetrofitInstance.getSearch(query).enqueue(object : Callback<MovieSearch> {
            override fun onResponse(call: Call<MovieSearch>, response: Response<MovieSearch>) {
                Log.d("TAG_X", "pass")
                resultLiveData.setValue(response.body())
            }

            override fun onFailure(call: Call<MovieSearch>, t: Throwable) {
                println("Fail")
                Log.d("TAB_X", "Fail")
            }
        })
    }

    fun getResultLiveData(): MutableLiveData<MovieSearch> {
        return resultLiveData
    }
}