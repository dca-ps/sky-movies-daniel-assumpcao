package br.com.danielassumpcao.skymovies.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Image(val height: String, val width: String, val url: String) : Serializable