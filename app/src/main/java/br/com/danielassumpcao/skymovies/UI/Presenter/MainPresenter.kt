package br.com.danielassumpcao.skymovies.UI.Presenter

import br.com.danielassumpcao.skymovies.Models.Movie
import br.com.danielassumpcao.skymovies.Services.MoviesService
import br.com.danielassumpcao.skymovies.Services.RetrofitConfig
import br.com.danielassumpcao.skymovies.UI.Activity.MainActivity
import br.com.danielassumpcao.skymovies.UI.Listeners.MoviesListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter {

    val PAGE_SIZE = 4

    var totalPage: Int = 0
    var totalMovies: Int = 0

    lateinit var allMoviesNames: List<String>
    lateinit var moviesDetail: ArrayList<Movie>


    fun getMovies(offset: Int, listener: MoviesListener) {
        if (this::allMoviesNames.isInitialized && allMoviesNames.size > 0) {
            val pageList: List<String> = allMoviesNames.subList(offset, offset + PAGE_SIZE)
            totalPage = pageList.size
            moviesDetail.clear()
            for (movie in pageList) {
                getMovieDetail(sanitizeMovieId(movie), listener)
            }

        } else {
            getMoviesNames(listener)
        }

    }

    fun sanitizeMovieId(movie: String): String {
        return movie.removePrefix("/title/").removeSuffix("/")
    }

    private fun getMoviesNames(listener: MoviesListener) {
        val service: MoviesService = RetrofitConfig().getMoviesService()

        val call = service.getPopularMovies()

        call.enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                response.body()?.let {
                    moviesDetail = ArrayList()
                    allMoviesNames = it
                    totalMovies = it.size
                    getMovies(0, listener)


                } ?: run {
                    listener.onMoviesFailure()
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                listener.onMoviesFailure()
            }
        })
    }


    private fun getMovieDetail(movie: String, listener: MoviesListener) {
        val service: MoviesService = RetrofitConfig().getMoviesService()

        val call = service.getMovieDetail(movie)

        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                response.body()?.let {
                    moviesDetail.add(it)
                    if (moviesDetail.size == totalPage) {
                        listener.onMoviesSucess(moviesDetail, totalMovies)
                    }
                } ?: run {
                    listener.onMoviesFailure()
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                listener.onMoviesFailure()
            }
        })
    }

}