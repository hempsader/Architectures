package com.example.architectures.add

import com.example.architectures.model.LocalDataSource
import com.example.architectures.model.Movie

class AddMoviePresenter(
    private val addMovieActivity: AddMovieContract.AddMovieInterface,
    private val localDataSource: LocalDataSource
) : AddMovieContract.AddMoviePresenterInterface {

    override fun addMovie(title: String, year: String, photoPath: String) {
        if (title.isNotEmpty()) {
            localDataSource.insert(Movie(title = title, releaseDate = year, posterPath = photoPath))
            addMovieActivity.success("Movie Added")
            addMovieActivity.returnToMain()
        } else {
            addMovieActivity.error("Please fill the title")
        }
    }

}