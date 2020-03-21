package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Min(

    @SerializedName("type") val type: String,
    @SerializedName("value") val value: Double
)