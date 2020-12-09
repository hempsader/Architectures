package com.example.architectures.add

import android.os.Build.VERSION_CODES.M
import com.example.architectures.BaseTest
import com.example.architectures.RxImmediateSchedulerRule
import com.example.architectures.model.LocalDataSource
import com.example.architectures.model.Movie
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AddMoviePresenterTest : BaseTest(){
    @Rule
    @JvmField var testSchedule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var mockLocalSource: LocalDataSource

    @Mock
    private lateinit var mockAddActivity: AddMovieContract.AddMovieInterface

    private lateinit var addPresenter: AddMoviePresenter

    @Before
    fun setUp() {
        addPresenter = AddMoviePresenter(mockAddActivity,mockLocalSource)
    }

    @Test
    fun addMovieTest(){
        val movie = Movie(title = "test", releaseDate = "2003", posterPath = "sss")
        Mockito.verify(mockLocalSource).insert(movie)
        addPresenter.addMovie(movie.title!!, movie.releaseDate!!,movie.posterPath!!)
        Mockito.verify(mockAddActivity).success("Movie Added")
    }
}