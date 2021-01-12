package br.com.danielassumpcao.skymovies

import br.com.danielassumpcao.skymovies.models.Image
import br.com.danielassumpcao.skymovies.models.Movie
import br.com.danielassumpcao.skymovies.models.PlotOutline
import br.com.danielassumpcao.skymovies.models.Title
import br.com.danielassumpcao.skymovies.utils.MovieUtils.Companion.getHourTime
import br.com.danielassumpcao.skymovies.utils.MovieUtils.Companion.getReleaseYear
import br.com.danielassumpcao.skymovies.utils.MovieUtils.Companion.sanitizeMovieId
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MovieUtilsTest {

    private val movieTest: Movie =  generateMovieMock()

    @Test
    fun sanitizeMovieIdTest() {
        val sanitizedId = sanitizeMovieId(movieTest.id!!)
        assertEquals(sanitizedId, "tt7126948")
    }



    @Test
    fun getHourTimeTest(){
        assertEquals(movieTest.getHourTime(), "1h 32min")
    }

    @Test
    fun getReleaseYearTest(){
        assertEquals(movieTest.getReleaseYear(), "2019")
    }


    private fun generateMovieMock(): Movie{
        val imageTest = Image("999", "999", "https://cutt.ly/gjbNMXd")
        val titleTest = Title("MovieTest", "92", imageTest)
        val plotOutlineTest = PlotOutline("PlotOutlineTest")

        return Movie("/title/tt7126948/", titleTest, ArrayList(), "2019-11-23", plotOutlineTest)
    }



}