package br.com.danielassumpcao.skymovies.models

import java.io.Serializable

data class Movie(val id: String?, val title: Title?, val genres: List<String>?, val releaseDate: String?, val plotOutline: PlotOutline?) : Serializable