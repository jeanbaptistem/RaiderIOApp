package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName

data class Ratings(

    @SerializedName("2v2") val v2: String,
    @SerializedName("3v3") val v3: String,
    @SerializedName("battlegrounds") val battlegrounds: String
)