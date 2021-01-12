package br.com.danielassumpcao.skymovies.ui.contract

import br.com.danielassumpcao.skymovies.models.Movie

interface MovieContract {
    interface View {
        fun stopLoading()
        fun onMoviesFailure()
        fun onMoviesSuccess(movies: List<Movie>, totalItens: Int)
    }

    interface Presenter {
        fun getMovies(offset: Int)
        fun getMoviesNames()
        fun getMovieDetail(movie: String)
    }
}