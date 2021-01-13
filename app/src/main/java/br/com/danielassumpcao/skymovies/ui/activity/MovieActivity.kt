package br.com.danielassumpcao.skymovies.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.danielassumpcao.skymovies.R
import br.com.danielassumpcao.skymovies.databinding.ActivityMovieBinding
import br.com.danielassumpcao.skymovies.models.Movie
import br.com.danielassumpcao.skymovies.ui.adapter.MovieAdapter
import br.com.danielassumpcao.skymovies.ui.contract.MovieContract
import br.com.danielassumpcao.skymovies.ui.listeners.MovieClickListener
import br.com.danielassumpcao.skymovies.ui.presenter.MoviePresenter
import com.google.android.material.snackbar.Snackbar

class MovieActivity : AppCompatActivity(), MovieClickListener, MovieContract.View {


    private lateinit var binding: ActivityMovieBinding

    private val presenter: MoviePresenter = MoviePresenter(this)


    private lateinit var adapter: MovieAdapter

    private var offset = 0
    private var totalItens = Int.MAX_VALUE
    private var isLoadingList = false

    /*
    * Lifecycle
    * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
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
        adapter = MovieAdapter(mutableListOf(), this, this)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.mainRV.layoutManager = layoutManager
        binding.mainRV.adapter = adapter
        binding.mainRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val gridLayoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager?
                if (!isLoadingList) {
                    val lastVisible = gridLayoutManager?.findLastCompletelyVisibleItemPositions(null)?.maxOrNull()

                    if (lastVisible == adapter.itemCount - 1) {
                        isLoadingList = true
                        loadItens()
                    }
                }
            }
        })
    }

    private fun loadItens() {
        if (adapter.itemCount < totalItens) {
            if (adapter.itemCount > 0 && isLoadingList) {
                onLoadingPage(true)
            } else {
                binding.swipeLayout.isRefreshing = true
            }
            presenter.getMovies(offset)
            offset += presenter.pageSize
        }
    }


    private fun onLoadingPage(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingLL.visibility = View.VISIBLE
        } else {
            binding.loadingLL.visibility = View.GONE
        }
    }

    private fun clearScreen() {
        offset = 0
        adapter.clear()
    }


    /*
    * Override Functions
    * */

    override fun stopLoading() {
        binding.swipeLayout.isRefreshing = false
        isLoadingList = false
        if (adapter.itemCount > 0) {
            onLoadingPage(false)
        }
    }

    override fun onMoviesSuccess(movies: List<Movie>, totalItens: Int) {
        this.totalItens = totalItens
        adapter.addMovies(movies)
    }

    override fun onMoviesFailure() {
        val newOffset = offset - presenter.pageSize
        if (newOffset >= 0) {
            offset = newOffset
        } else {
            clearScreen()
        }

        Snackbar.make(binding.mainRV, R.string.load_error, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.reload) {
                loadItens()
            }.show()
    }

    override fun onMovieClickListener(clickedMovie: Movie) {
        DetailActivity().startActivity(this, clickedMovie)
    }

}


