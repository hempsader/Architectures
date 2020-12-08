package com.example.architectures.main

import com.example.architectures.model.Movie

class SearhActivityContract {
    interface SearchActivityPresenterInterface {
        fun fetchMovieList(query: String)
    }
    interface SearchActivityView{
        fun getListMovies(movie: List<Movie>)
        fun error(message: String)
        fun success(message: String)
    }
}