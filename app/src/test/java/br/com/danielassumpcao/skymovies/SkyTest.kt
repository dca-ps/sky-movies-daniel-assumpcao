package br.com.danielassumpcao.skymovies

import br.com.danielassumpcao.skymovies.Models.Movie
import br.com.danielassumpcao.skymovies.Models.Title
import br.com.danielassumpcao.skymovies.UI.Activity.MainActivity
import br.com.danielassumpcao.skymovies.UI.Presenter.MainPresenter
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SkyTest {

    lateinit var movieTest: Movie

    @Before
    fun before(){
        val titleTest = Title()
        titleTest.duration = "92"

        movieTest = Movie()
        movieTest.id = "/title/tt7126948/"
        movieTest.releaseDate = "2019-11-23"
        movieTest.title = titleTest
    }

    @Test
    fun sanitizeMovieIdTest() {
        val presenter = MainPresenter()

        val sanitizedId = presenter.sanitizeMovieId(movieTest.id)

        assertEquals(sanitizedId, "tt7126948")
    }

    @Test
    fun getLastMovieScreenTest() {
        val activity = MainActivity()
        val numbersArray = intArrayOf(10,15,89,45,12)

        val maxNumber = activity.getLastMovieScreen(numbersArray)

        assertEquals(maxNumber, 89)
    }

    @Test
    fun getHourTimeTest(){
        assertEquals(movieTest.getHourTime(), "1h 32min")
    }

    @Test
    fun getFormattedReleaseDateTest(){
        assertEquals(movieTest.getFormattedReleaseDate(), "23/11/2019")
    }



}