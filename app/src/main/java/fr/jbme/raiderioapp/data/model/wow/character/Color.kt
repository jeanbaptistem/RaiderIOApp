package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Color(

    @SerializedName("a") val a: Int,
    @SerializedName("b") val b: Int,
    @SerializedName("g") val g: Int,
    @SerializedName("r") val r: Int
)