package br.com.danielassumpcao.skymovies.utils

import br.com.danielassumpcao.skymovies.models.Movie
import java.time.LocalDate

object MovieUtils {

        fun Movie.getHourTime(): String {
            var durationMinutes = 0
            title?.runningTimeInMinutes?.let{
                durationMinutes = it.toInt()
            }
            if(durationMinutes == 0){
                return "No duration released"
            }
            return (durationMinutes / 60).toString() + "h " + durationMinutes % 60 + "min"
        }

        fun Movie.getReleaseYear(): String {
            val date = LocalDate.parse(releaseDate)
            return date.year.toString()
        }

        fun sanitizeMovieId(movie: String): String {
            return movie.removePrefix("/title/").removeSuffix("/")
        }

}






