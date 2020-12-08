package com.example.architectures.main

import com.example.architectures.model.Movie

class AddMovieContract {
    interface AddMoviePresenterInterface{
        fun addMovie(title: String, year: String, photoPath: String)
    }

    interface AddMovieInterface{
        fun returnToMain()
        fun error(message: String)
        fun success(message: String)
    }
}