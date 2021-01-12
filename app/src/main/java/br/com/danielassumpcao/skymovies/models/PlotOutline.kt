package br.com.danielassumpcao.skymovies.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PlotOutline : Serializable {

    @SerializedName("text")
    lateinit var text: String

}