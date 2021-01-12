package br.com.danielassumpcao.skymovies.ui.presenter

import br.com.danielassumpcao.skymovies.models.Movie
import br.com.danielassumpcao.skymovies.services.MoviesService
import br.com.danielassumpcao.skymovies.services.RetrofitConfig
import br.com.danielassumpcao.skymovies.ui.contract.MovieContract
import br.com.danielassumpcao.skymovies.utils.MovieUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePresenter(private val view: MovieContract.View) : MovieContract.Presenter {


    val pageSize = 4

    private var totalPage: Int = 0
    private var totalMovies: Int = 0

    private lateinit var allMoviesNames: List<String>
    private val moviesDetail: ArrayList<Movie> = ArrayList()


    override fun getMovies(offset: Int) {
        if (this::allMoviesNames.isInitialized && allMoviesNames.isNotEmpty()) {
            val pageList: List<String> = allMoviesNames.subList(offset, offset + pageSize)
            totalPage = pageList.size
            moviesDetail.clear()
            for (movie in pageList) {
                getMovieDetail(MovieUtils.sanitizeMovieId(movie))
            }

        } else {
            getMoviesNames()
        }

    }



    override fun getMoviesNames() {
        val service: MoviesService = RetrofitConfig.getMoviesService()

        val call = service.getPopularMovies()

        call.enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                response.body()?.let {
                    moviesDetail.clear()
                    allMoviesNames = it
                    totalMovies = it.size
                    getMovies(0)


                } ?: run {
                    view.stopLoading()
                    view.onMoviesFailure()
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                view.stopLoading()
                view.onMoviesFailure()
            }
        })
    }


    override fun getMovieDetail(movie: String) {
        val service: MoviesService = RetrofitConfig.getMoviesService()

        val call = service.getMovieDetail(movie)

        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                response.body()?.let {
                    moviesDetail.add(it)
                    if (moviesDetail.size == totalPage) {
                        view.stopLoading()
                        view.onMoviesSuccess(moviesDetail, totalMovies)
                    }
                } ?: run {
                    view.stopLoading()
                    view.onMoviesFailure()
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                view.stopLoading()
                view.onMoviesFailure()
            }
        })
    }

}