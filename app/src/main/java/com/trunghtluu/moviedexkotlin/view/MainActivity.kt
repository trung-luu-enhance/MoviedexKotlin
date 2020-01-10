package com.trunghtluu.moviedexkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.trunghtluu.moviedexkotlin.R

import com.trunghtluu.moviedexkotlin.viewmodel.MainViewModel
import com.trunghtluu.moviedexkotlin.model.MovieSearch
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DividerItemDecoration
import com.trunghtluu.moviedexkotlin.adapter.MovieAdapter
import com.trunghtluu.moviedexkotlin.model.MovieResult

import android.widget.EditText
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    lateinit var observer: Observer<MovieSearch>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        observer = object : Observer<MovieSearch> {
            override fun onChanged(movieSearch: MovieSearch) {
                setupRV(movieSearch.results)
            }
        }

        main_search_bt.setOnClickListener{
            mainViewModel.getSearches(main_search_et.getText().toString())
        }

        mainViewModel.getResultLiveData().observe(this@MainActivity, observer)
    }

    private fun setupRV(response: List<MovieResult>) {
        val adapter : MovieAdapter = MovieAdapter(response, this)
        val itemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        main_search_rv.addItemDecoration(itemDecoration)
        main_search_rv.setLayoutManager(LinearLayoutManager(this@MainActivity))
        main_search_rv.setAdapter(adapter)
    }
}
