package br.com.danielassumpcao.skymovies.UI.Listeners

import br.com.danielassumpcao.skymovies.Models.Movie

interface MovieClickListener {
    fun onMovieClickListener(clickedMovie: Movie)
}