package br.com.danielassumpcao.skymovies.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.danielassumpcao.skymovies.R
import br.com.danielassumpcao.skymovies.databinding.ActivityDetailBinding
import br.com.danielassumpcao.skymovies.models.Movie
import com.bumptech.glide.Glide


class DetailActivity : AppCompatActivity() {
    private val extraMovie: String = "EXTRA_MOVIE"


    private lateinit var binding: ActivityDetailBinding

    fun startActivity(callerActivity: Activity, movie: Movie) {
        val intent = Intent(callerActivity, this::class.java).apply {
            putExtra(extraMovie, movie)
        }
        callerActivity.startActivity(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val movie = intent.getSerializableExtra(extraMovie) as? Movie

        setupViews(movie)
    }

    fun setupViews(movie: Movie?) {


        binding.backButton.setOnClickListener{
            onBackPressed()
        }
        binding.titleTV.text = movie?.title?.title
        binding.releaseYearTV.text = movie?.getReleaseYear()
        binding.durationTV.text = movie?.getHourTime()

        lateinit var overViewText: String

        movie?.plotOutline?.let{
            overViewText = it.text
        } ?: run {
            overViewText = getString(R.string.detail_no_overview)
        }


        binding.overviewTV.text = overViewText
        binding.genreTV.text = movie?.genres?.first()

        Glide.with(this)
            .load(movie?.title?.image?.url)
            .placeholder(R.drawable.sky_placeholder)
            .centerCrop()
            .into(binding.coverIV);

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}


