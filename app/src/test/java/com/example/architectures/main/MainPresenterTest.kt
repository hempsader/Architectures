package com.example.architectures.main

import com.example.architectures.BaseTest
import com.example.architectures.RxImmediateSchedulerRule
import com.example.architectures.model.LocalDataSource
import com.example.architectures.model.Movie
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest : BaseTest(){
    @Rule @JvmField var testSchedule = RxImmediateSchedulerRule()
    @Mock
    private lateinit var mockActivity: MainContract.ViewInterface

    @Mock
    private lateinit var mockDataSource: LocalDataSource

    lateinit var mainPresenter: MainPresenter

    @Before
    fun setUp(){
        mainPresenter = MainPresenter(mockActivity, mockDataSource)
    }

    private val dummyMovies: ArrayList<Movie>
            get()  {
                val mockedData = ArrayList<Movie>()
                mockedData.add(Movie(title ="Title1", releaseDate = "ReleaseDate1", posterPath = "PosterPath1"))
                mockedData.add(Movie(title= "Title2", releaseDate = "ReleaseDate2",posterPath = "PosterPath2"))
                mockedData.add(Movie(title = "Title3",releaseDate=  "ReleaseDate3",posterPath =  "PosterPath3"))
                mockedData.add(Movie(title = "Title4", releaseDate = "ReleaseDate4",posterPath =  "PosterPath4"))
                return mockedData
            }
    @Test
    fun testGetMyMovieList(){
        val dummy = dummyMovies
        Mockito.doReturn(Observable.just(dummy)).`when`(mockDataSource).allMovies
        mainPresenter.getMoviesList()
        Mockito.verify(mockDataSource).allMovies
        Mockito.verify(mockActivity).displayMovies(dummy)
    }

    @Test
    fun testGetMyMovieListEmpty(){
        val dummy = ArrayList<Movie>()
        Mockito.doReturn(Observable.just(dummy)).`when`(mockDataSource).allMovies
        mainPresenter.getMoviesList()
        Mockito.verify(mockDataSource).allMovies
        Mockito.verify(mockActivity).displayMovies(dummy)
    }

    val deleteSingleMovie: HashSet<Movie>
        get()  {
            val deleteMovieSet = HashSet<Movie>()
            deleteMovieSet.add(dummyMovies.get(2))
            return deleteMovieSet
        }

    @Test
    fun testDeleteSingleMovie(){
        val myDeleteValue = deleteSingleMovie
        for(movie in myDeleteValue) {
            mainPresenter.deleteMovie(movie)
            Mockito.doReturn(mockDataSource).`when`(mockDataSource.delete(movie))
        }
        Mockito.verify(mockActivity).displayMessage("Movie Deleted")

    }

}