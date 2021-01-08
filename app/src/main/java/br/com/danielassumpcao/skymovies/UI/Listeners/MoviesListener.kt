package br.com.danielassumpcao.skymovies.UI.Listeners

import br.com.danielassumpcao.skymovies.Models.Movie

interface MoviesListener {

    fun onMoviesSucess(movies: List<Movie>, totalItens: Int)
    fun onMoviesFailure()

}