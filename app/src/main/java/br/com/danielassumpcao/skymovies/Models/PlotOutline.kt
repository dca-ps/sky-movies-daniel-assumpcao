package br.com.danielassumpcao.skymovies.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PlotOutline : Serializable {
    @SerializedName("text")
    lateinit var text: String
}