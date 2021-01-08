package br.com.danielassumpcao.skymovies.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import br.com.danielassumpcao.skymovies.Models.Movie
import br.com.danielassumpcao.skymovies.R
import br.com.danielassumpcao.skymovies.UI.Listeners.MovieClickListener
import com.squareup.picasso.Picasso

class MainAdapter(val movies: List<Movie>, val context: Context, val listener: MovieClickListener) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val thisMovie: Movie = movies.get(position)

        holder.movieTitleTV.setText(thisMovie.title.title)
        Picasso
            .get()
            .load(thisMovie.title.image.url)
            .placeholder(R.drawable.sky_placeholder)
            .fit()
            .centerInside()
            .into(holder.movieCoverIV);

        holder.movieCoverIV.setOnClickListener {
            listener.onMovieClickListener(thisMovie)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movieTitleTV: TextView
        var movieCoverIV: ImageView

        init {
            movieTitleTV = itemView.findViewById(R.id.movieTitleTV)
            movieCoverIV = itemView.findViewById(R.id.movieCoverIV)


        }
    }

}
