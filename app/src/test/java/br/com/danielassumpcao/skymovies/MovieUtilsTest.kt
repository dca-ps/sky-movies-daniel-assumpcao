package br.com.danielassumpcao.skymovies

import br.com.danielassumpcao.skymovies.Utils.generateMovieMock
import br.com.danielassumpcao.skymovies.models.Movie
import br.com.danielassumpcao.skymovies.utils.MovieUtils.getHourTime
import br.com.danielassumpcao.skymovies.utils.MovieUtils.getReleaseYear
import br.com.danielassumpcao.skymovies.utils.MovieUtils.sanitizeMovieId
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MovieUtilsTest {

    private val movieTest: Movie = generateMovieMock()

    @Test
    fun sanitizeMovieIdTest() {
        val sanitizedId = sanitizeMovieId(movieTest.id!!)

        assertEquals(sanitizedId, "tt7126948")
    }


    @Test
    fun getHourTimeTest() {
        assertEquals(movieTest.getHourTime(), "1h 32min")
    }

    @Test
    fun getReleaseYearTest() {
        assertEquals(movieTest.getReleaseYear(), "2019")
    }


}