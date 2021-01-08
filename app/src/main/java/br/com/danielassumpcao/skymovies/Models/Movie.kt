package br.com.danielassumpcao.skymovies.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Movie : Serializable {
    @SerializedName("id")
    lateinit var id: String

    @SerializedName("title")
    lateinit var title: Title

    @SerializedName("genres")
    lateinit var genres: List<String>

    @SerializedName("releaseDate")
    lateinit var releaseDate: String

    @SerializedName("plotOutline")
    lateinit var plotOutline: PlotOutline





    fun getHourTime(): String {
        val durationMinutes: Int = title.duration.toInt()
        return (durationMinutes / 60).toString() + "h " + durationMinutes % 60 + "min"
    }

    fun getFormattedReleaseDate(): String{
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val date = LocalDate.parse(releaseDate)

        return date.format(formatter)
    }

    fun getReleaseYear(): String{
        val date = LocalDate.parse(releaseDate)
        return date.year.toString()
    }

}