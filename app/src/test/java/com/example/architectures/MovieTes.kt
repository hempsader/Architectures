package com.example.architectures

import com.example.architectures.model.Movie
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test

class MovieTes {

    @Test
    fun testReleaseYearFromString(){
        val movie = Movie(title = "Finding nemo",releaseDate = "2003-05-30")
        assertEquals("2003", movie.getReleaseYearFromDate())
    }

    @Test
    fun testReleaseYearFromYear(){
        val movie = Movie(title = "Finding nemo",releaseDate = "2003")
        assertEquals("2003", movie.getReleaseYearFromDate())
    }

    @Test
    fun testReleaseYearFromEmpty(){
        val movie = Movie(title = "Finding nemo",releaseDate = "")
        assertEquals("2003", movie.getReleaseYearFromDate())
    }

    @Test
    fun testReleaseYearFromNull(){
        val movie = Movie(title = "Finding nemo")
        assertEquals("2003", movie.getReleaseYearFromDate())
    }
}