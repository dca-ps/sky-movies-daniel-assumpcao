package br.com.danielassumpcao.skymovies.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.danielassumpcao.skymovies.R
import br.com.danielassumpcao.skymovies.models.Movie
import br.com.danielassumpcao.skymovies.ui.listeners.MovieClickListener
import com.bumptech.glide.Glide

class MainAdapter(val movies: List<Movie>, val context: Context, val listener: MovieClickListener) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val thisMovie: Movie = movies.get(position)

        holder.movieTitleTV.text = thisMovie.title.title
        Glide.with(context)
            .load(thisMovie.title.image.url)
            .placeholder(R.drawable.sky_placeholder)
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
        var movieTitleTV: TextView = itemView.findViewById(R.id.movieTitleTV)
        var movieCoverIV: ImageView = itemView.findViewById(R.id.movieCoverIV)

    }

}
