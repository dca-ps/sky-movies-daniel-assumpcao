package br.com.danielassumpcao.skymovies.ui.listeners

import br.com.danielassumpcao.skymovies.models.Movie

interface MoviesListener {

    fun onMoviesSucess(movies: List<Movie>, totalItens: Int)
    fun onMoviesFailure()

}