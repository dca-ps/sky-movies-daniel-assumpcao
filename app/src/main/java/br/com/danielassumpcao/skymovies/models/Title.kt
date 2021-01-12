package br.com.danielassumpcao.skymovies.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Title : Serializable {

    @SerializedName("title")
    lateinit var title: String

    @SerializedName("runningTimeInMinutes")
    lateinit var duration: String

    @SerializedName("image")
    lateinit var image: Image

}