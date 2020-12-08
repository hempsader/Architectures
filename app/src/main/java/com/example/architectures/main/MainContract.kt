package com.example.architectures.main

import com.example.architectures.model.Movie

class MainContract {
    interface PresenterInterface {
        fun getMoviesList()
    }

    interface ViewInterface{
        fun displayMovies(movieList: List<Movie>)
        fun displayNoMovies()
        fun displayMessage(message: String)
        fun displayError(message: String)
    }
}