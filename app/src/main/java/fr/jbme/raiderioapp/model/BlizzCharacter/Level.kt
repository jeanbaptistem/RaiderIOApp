package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Level(

    @SerializedName("display_string") val display_string: String,
    @SerializedName("value") val value: Int
)