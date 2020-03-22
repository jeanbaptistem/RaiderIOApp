package fr.jbme.raiderioapp.model.blizzard.itemInfo

import com.google.gson.annotations.SerializedName

data class Color(

    @SerializedName("r") val r: Int,
    @SerializedName("g") val g: Int,
    @SerializedName("b") val b: Int,
    @SerializedName("a") val a: Int
)