package br.com.danielassumpcao.skymovies.UI.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.danielassumpcao.skymovies.Models.Movie
import br.com.danielassumpcao.skymovies.R
import butterknife.BindView
import butterknife.ButterKnife
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private val EXTRA_MOVIE: String = "EXTRA_MOVIE"

    fun startActivity(callerActivity: Activity, movie: Movie) {
        val intent = Intent(callerActivity, this::class.java).apply {
            putExtra(EXTRA_MOVIE, movie)
        }
        callerActivity.startActivity(intent)
    }

    @BindView(R.id.coverIV)
    lateinit var coverIV: ImageView

    @BindView(R.id.backButton)
    lateinit var backButton: ImageView

    @BindView(R.id.titleTV)
    lateinit var titleTV: TextView

    @BindView(R.id.releaseYearTV)
    lateinit var releaseYearTV: TextView

    @BindView(R.id.durationTV)
    lateinit var durationTV: TextView

    @BindView(R.id.overviewTV)
    lateinit var overviewTV: TextView

    @BindView(R.id.genreTV)
    lateinit var genreTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        ButterKnife.bind(this)

        val movie = intent.getSerializableExtra(EXTRA_MOVIE) as? Movie

        setupViews(movie)
    }

    fun setupViews(movie: Movie?) {


        backButton.setOnClickListener{
            onBackPressed()
        }
        titleTV.setText(movie?.title?.title)
        releaseYearTV.setText(movie?.getReleaseYear())
        durationTV.setText(movie?.getHourTime())

        lateinit var overViewText: String

        movie?.plotOutline?.let{
            overViewText = it.text
        } ?: run {
            overViewText = getString(R.string.detail_no_overview)
        }


        overviewTV.setText(overViewText)
        genreTV.setText(movie?.genres?.first())

        Picasso
            .get()
            .load(movie?.title?.image?.url)
            .placeholder(R.drawable.sky_placeholder)
            .fit()
            .centerCrop()
            .into(coverIV);

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}


