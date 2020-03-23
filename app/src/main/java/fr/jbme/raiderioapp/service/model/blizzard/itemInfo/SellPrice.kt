package fr.jbme.raiderioapp.service.model.blizzard.itemInfo

import com.google.gson.annotations.SerializedName


data class SellPrice(

    @SerializedName("value") val value: Int,
    @SerializedName("display_strings") val display_strings: DisplayStrings
)