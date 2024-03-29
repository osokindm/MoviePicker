package com.example.moviepicker.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepicker.R
import com.example.moviepicker.data.model.movie.Film

class FavouritesAdapter(val fragment: Fragment, var favouriteMoviesList: MutableList<Film>) :
    RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder>() {
    fun updateList(newList: MutableList<Film>) {
        favouriteMoviesList = newList
        notifyDataSetChanged() //or you can implement a DiffUtil.Callback
    }

    fun notifyRemoved(i: Int) {
        try {
            favouriteMoviesList.removeAt(i)
        } catch (e: IndexOutOfBoundsException) {
            Log.e("FavouritesAdapter", e.stackTraceToString())
        }
        notifyItemRemoved(i)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_movie_card, parent, false)
        return FavouritesViewHolder(v)
    }

    override fun getItemCount(): Int {
        return favouriteMoviesList.size
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        // todo implement network error message as it is done in MoviesPagedListAdapter
        holder.bindView(position)
        holder.movieCard.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("movieId", favouriteMoviesList[position].kinopoiskId)
            fragment.findNavController().navigate(R.id.descriptionFragment, bundle)
        }
    }

    inner class FavouritesViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var movieCard: View
        var movieTitle: TextView
        var moviePoster: ImageView
        var movieRating: TextView
        var movieYear: TextView

        init {
            movieCard = view.findViewById(R.id.movieCard)
            movieTitle = view.findViewById(R.id.movieTitle)
            moviePoster = view.findViewById(R.id.moviePoster)
            movieRating = view.findViewById(R.id.movieRating)
            movieYear = view.findViewById(R.id.movieYear)
            view.setOnClickListener(this)
        }

        fun bindView(position: Int) {
            Glide.with(fragment).load(favouriteMoviesList[position].posterUrl).into(moviePoster)
            movieTitle.text = favouriteMoviesList[position].nameRu
            movieRating.text =
                (favouriteMoviesList[position].ratingKinopoisk ?: (favouriteMoviesList[position].ratingImdb
                    ?: (favouriteMoviesList[position].ratingFilmCritics ?: "-"))).toString()
//            Log.e("FavouritesAdapter", favouriteMoviesList[position].data.ratingKinopoisk.toString())
//            movieRating.text = rating.toString()
            movieYear.text = favouriteMoviesList[position].year.toString()

        }

        override fun onClick(view: View) {
            // TODO: implement genre buttons onClick
        }
    }

}