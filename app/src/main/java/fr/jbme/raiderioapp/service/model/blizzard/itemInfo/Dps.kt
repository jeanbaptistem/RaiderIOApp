package fr.jbme.raiderioapp.service.model.blizzard.itemInfo

import com.google.gson.annotations.SerializedName

data class Dps(

    @SerializedName("value") val value: Double,
    @SerializedName("display_string") val display_string: String
)