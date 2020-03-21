package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName

data class Value(

    @SerializedName("type") val type: String,
    @SerializedName("value") val value: Double,
    @SerializedName("max") var max: Max?,
    @SerializedName("min") var min: Min?
)