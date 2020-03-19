package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Sell_price(

    @SerializedName("display_strings") val display_strings: DisplayStrings,
    @SerializedName("value") val value: Int
)