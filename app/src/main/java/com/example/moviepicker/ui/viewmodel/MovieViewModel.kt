package com.example.moviepicker.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviepicker.data.api.FilmDetailsRepository
import com.example.moviepicker.data.model.movie.Film
import com.example.moviepicker.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MovieViewModel(private val filmRepository: FilmDetailsRepository, movieId: Int) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val movieDetails: LiveData<Film> by lazy {
        filmRepository.fetchFilmDetails(compositeDisposable, movieId)
    }

    val networkState: LiveData<NetworkState> by lazy {
        filmRepository.getFilmDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}