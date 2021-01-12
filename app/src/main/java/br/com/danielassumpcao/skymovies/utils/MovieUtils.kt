package br.com.danielassumpcao.skymovies.utils

import br.com.danielassumpcao.skymovies.models.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieUtils {
    companion object {

        fun Movie.getHourTime(): String {
            var durationMinutes: Int = 0
            title?.runningTimeInMinutes?.let{
                durationMinutes = it.toInt()
            }
            if(durationMinutes == 0){
                return "No duration released"
            }
            return (durationMinutes / 60).toString() + "h " + durationMinutes % 60 + "min"
        }


        fun Movie.getFormattedReleaseDate(): String {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val date = LocalDate.parse(releaseDate)

            return date.format(formatter)
        }

        fun Movie.getReleaseYear(): String {
            val date = LocalDate.parse(releaseDate)
            return date.year.toString()
        }
    }
}






