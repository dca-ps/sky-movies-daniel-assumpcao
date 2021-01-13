package br.com.danielassumpcao.skymovies

import br.com.danielassumpcao.skymovies.Utils.generateMovieMock
import br.com.danielassumpcao.skymovies.models.Movie
import br.com.danielassumpcao.skymovies.ui.contract.MovieContract
import br.com.danielassumpcao.skymovies.ui.presenter.MoviePresenter
import br.com.danielassumpcao.skymovies.utils.MovieUtils
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyArray
import com.nhaarman.mockitokotlin2.timeout
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MoviePresenterTest {
    @Mock
    private lateinit var view: MovieContract.View

    private lateinit var presenter: MoviePresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this);
        presenter = MoviePresenter(view)
    }

    @Test
    fun getMoviesTestSuccess(){
        presenter.getMovies(0)

        verify(view, timeout(10000)).stopLoading()
        verify(view, timeout(10000)).onMoviesSuccess(anyList<Movie>(), any())

    }

    @Test
    fun getMovieDetailTestFail(){
        presenter.getMovieDetail("testFail")

        verify(view, timeout(5000)).stopLoading()
        verify(view, timeout(5000)).onMoviesFailure()
    }



}