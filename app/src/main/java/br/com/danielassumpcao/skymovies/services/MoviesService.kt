package br.com.danielassumpcao.skymovies.services

import br.com.danielassumpcao.skymovies.models.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("/title/get-most-popular-movies")
    fun getPopularMovies(): Call<List<String>>

    @GET("/title/get-overview-details")
    fun getMovieDetail(@Query("tconst") tconst: String): Call<Movie>

}