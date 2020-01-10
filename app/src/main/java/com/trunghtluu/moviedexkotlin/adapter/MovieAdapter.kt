package com.trunghtluu.moviedexkotlin.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trunghtluu.moviedexkotlin.model.MovieResult
import android.view.LayoutInflater
import com.trunghtluu.moviedexkotlin.R
import android.widget.TextView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.trunghtluu.moviedexkotlin.utils.Constants
import kotlinx.android.synthetic.main.movie_card.view.*


class MovieAdapter(var movieResultList : List<MovieResult>,
                   var applicationContext: Context) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    interface MovieAdapterDelegate {
        fun movieSelected(selected: MovieResult)
    }

    init {
        this.movieResultList = movieResultList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        applicationContext = parent.context.applicationContext

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_card, parent, false)

        return MovieViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return movieResultList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.nameTextView
            .setText("Title: " + movieResultList.get(position).getTitle())
        holder.yearTextView
            .setText("Release Date: " + movieResultList.get(position).getReleaseDate())
        holder.ratingTextView
            .setText("Rating: " + movieResultList.get(position).getVoteAverage())
        Glide.with(applicationContext)
            .load(Constants.IMAGE_BASE + movieResultList.get(position).getPosterPath())
            .into(holder.posterImageView)
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView = itemView.name_tv
        var ratingTextView: TextView = itemView.rating_tv
        var yearTextView: TextView = itemView.year_tv
        var posterImageView: ImageView = itemView.poster_iv
    }
}