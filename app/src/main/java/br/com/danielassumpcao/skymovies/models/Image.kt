package br.com.danielassumpcao.skymovies.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Image : Serializable {

    @SerializedName("height")
    lateinit var height: String

    @SerializedName("width")
    lateinit var width: String

    @SerializedName("url")
    lateinit var url: String
}