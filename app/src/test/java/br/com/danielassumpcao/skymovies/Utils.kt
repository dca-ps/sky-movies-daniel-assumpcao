package br.com.danielassumpcao.skymovies

import br.com.danielassumpcao.skymovies.models.Image
import br.com.danielassumpcao.skymovies.models.Movie
import br.com.danielassumpcao.skymovies.models.PlotOutline
import br.com.danielassumpcao.skymovies.models.Title

object Utils {

    fun generateMovieMock(): Movie {
        val imageTest = Image("999", "999", "https://cutt.ly/gjbNMXd")
        val titleTest = Title("MovieTest", "92", imageTest)
        val plotOutlineTest = PlotOutline("PlotOutlineTest")

        return Movie("/title/tt7126948/", titleTest, ArrayList(), "2019-11-23", plotOutlineTest)
    }
}