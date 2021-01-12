package br.com.danielassumpcao.skymovies.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Title(val title: String, val runningTimeInMinutes: String, val image: Image) : Serializable