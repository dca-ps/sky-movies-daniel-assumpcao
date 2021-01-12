package br.com.danielassumpcao.skymovies.ui.listeners

import br.com.danielassumpcao.skymovies.models.Movie

interface MovieClickListener {

    fun onMovieClickListener(clickedMovie: Movie)

}