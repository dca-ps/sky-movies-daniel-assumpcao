package br.com.danielassumpcao.skymovies.UI.Activity

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.danielassumpcao.skymovies.Models.Movie
import br.com.danielassumpcao.skymovies.R
import br.com.danielassumpcao.skymovies.UI.Adapter.MainAdapter
import br.com.danielassumpcao.skymovies.UI.Listeners.MovieClickListener
import br.com.danielassumpcao.skymovies.UI.Listeners.MoviesListener
import br.com.danielassumpcao.skymovies.UI.Presenter.MainPresenter
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), MoviesListener, MovieClickListener {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.recycleView)
    lateinit var mainRV: RecyclerView

    @BindView(R.id.swipeLayout)
    lateinit var swipeLayout: SwipeRefreshLayout

    @BindView(R.id.loadingLL)
    lateinit var loadingLL: LinearLayout


    lateinit var presenter: MainPresenter

    lateinit var movies: MutableList<Movie>

    lateinit var adapter: MainAdapter

    var offset = 0
    var totalItens = Int.MAX_VALUE
    var isLoadingList = false

    /*
    * Lifecycle
    * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)

        presenter = MainPresenter()
        movies = mutableListOf()

        setupViews()
        loadItens()
    }


    /*
    * Logic Functions
    * */

    fun setupViews() {
        swipeLayout.setOnRefreshListener {
            if (!isLoadingList) {
                clearScreen()
                loadItens()
            } else {
                swipeLayout.isRefreshing = false
            }
        }
        this.adapter = MainAdapter(movies, this, this)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mainRV.layoutManager = layoutManager
        mainRV.adapter = this.adapter
        mainRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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

    fun loadItens() {
        if (movies.size < totalItens) {
            if (movies.size > 0 && isLoadingList) {
                onLoadingPage(true)
            } else {
                swipeLayout.isRefreshing = true
            }
            presenter.getMovies(offset, this)
            offset += presenter.PAGE_SIZE
        }
    }

    fun stopMiddleScreenLoading() {
        if (movies.size > 0) {
            onLoadingPage(false)
        }
        isLoadingList = false
    }

    fun onLoadingPage(isLoading: Boolean) {
        if (isLoading) {
            loadingLL.visibility = View.VISIBLE
        } else {
            loadingLL.visibility = View.GONE
        }
    }


    fun getLastMovieScreen(movieList: IntArray?): Int? {
        return movieList?.maxOrNull()
    }

    fun clearScreen() {
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
        this.swipeLayout.isRefreshing = false

    }

    override fun onMoviesFailure() {
        this.swipeLayout.isRefreshing = false
        isLoadingList = false
        loadingLL.visibility = View.GONE

        val newOffset = offset - presenter.PAGE_SIZE
        if (newOffset >= 0) {
            offset = newOffset
        } else {
            clearScreen()
        }

        Snackbar.make(mainRV, R.string.load_error, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.reload, View.OnClickListener {
                loadItens()
            }).show()
    }

    override fun onMovieClickListener(clickedMovie: Movie) {
        DetailActivity().startActivity(this, clickedMovie)
    }

}


