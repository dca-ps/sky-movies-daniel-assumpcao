package br.com.danielassumpcao.skymovies.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.danielassumpcao.skymovies.R
import br.com.danielassumpcao.skymovies.databinding.ActivityMainBinding
import br.com.danielassumpcao.skymovies.models.Movie
import br.com.danielassumpcao.skymovies.ui.adapter.MainAdapter
import br.com.danielassumpcao.skymovies.ui.listeners.MovieClickListener
import br.com.danielassumpcao.skymovies.ui.listeners.MoviesListener
import br.com.danielassumpcao.skymovies.ui.presenter.MainPresenter
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), MoviesListener, MovieClickListener {



    private lateinit var binding: ActivityMainBinding

    val presenter: MainPresenter = MainPresenter()

    val movies: MutableList<Movie> = mutableListOf()

    lateinit var adapter: MainAdapter

    var offset = 0
    var totalItens = Int.MAX_VALUE
    var isLoadingList = false

    /*
    * Lifecycle
    * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)

        setupViews()
        loadItens()
    }


    /*
    * Logic Functions
    * */

    private fun setupViews() {
        binding.swipeLayout.setOnRefreshListener {
            if (!isLoadingList) {
                clearScreen()
                loadItens()
            } else {
                binding.swipeLayout.isRefreshing = false
            }
        }
        this.adapter = MainAdapter(movies, this, this)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.mainRV.layoutManager = layoutManager
        binding.mainRV.adapter = this.adapter
        binding.mainRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val gridLayoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager?
                if (!isLoadingList) {
                    val lastVisible = getLastMovieScreen(
                        gridLayoutManager?.findLastCompletelyVisibleItemPositions(null)
                    )
                    if (lastVisible == movies.size - 1) {
                        isLoadingList = true
                        loadItens()
                    }
                }
            }
        })
    }

    private fun loadItens() {
        if (movies.size < totalItens) {
            if (movies.size > 0 && isLoadingList) {
                onLoadingPage(true)
            } else {
                binding.swipeLayout.isRefreshing = true
            }
            presenter.getMovies(offset, this)
            offset += presenter.pageSize
        }
    }

    private fun stopMiddleScreenLoading() {
        if (movies.size > 0) {
            onLoadingPage(false)
        }
        isLoadingList = false
    }

    private fun onLoadingPage(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingLL.visibility = View.VISIBLE
        } else {
            binding.loadingLL.visibility = View.GONE
        }
    }


    private fun getLastMovieScreen(movieList: IntArray?): Int? {
        return movieList?.maxOrNull()
    }

    private fun clearScreen() {
        offset = 0
        this.movies.clear()
        this.adapter.notifyDataSetChanged()
    }

    /*
    * Override Functions
    * */

    override fun onMoviesSucess(movies: List<Movie>, totalItens: Int) {
        this.totalItens = totalItens
        stopMiddleScreenLoading()

        this.movies.addAll(movies)
        this.adapter.notifyDataSetChanged()
        this.binding.swipeLayout.isRefreshing = false

    }

    override fun onMoviesFailure() {
        this.binding.swipeLayout.isRefreshing = false
        isLoadingList = false
        binding.loadingLL.visibility = View.GONE

        val newOffset = offset - presenter.pageSize
        if (newOffset >= 0) {
            offset = newOffset
        } else {
            clearScreen()
        }

        Snackbar.make(binding.mainRV, R.string.load_error, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.reload, View.OnClickListener {
                loadItens()
            }).show()
    }

    override fun onMovieClickListener(clickedMovie: Movie) {
        DetailActivity().startActivity(this, clickedMovie)
    }

}


