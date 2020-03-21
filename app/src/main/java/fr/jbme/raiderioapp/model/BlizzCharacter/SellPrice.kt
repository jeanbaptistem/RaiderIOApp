package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class SellPrice(

    @SerializedName("display_strings") val display_strings: DisplayStrings,
    @SerializedName("value") val value: Int
)