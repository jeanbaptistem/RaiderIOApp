package fr.jbme.raiderioapp.service.model.blizzard.itemInfo

import com.google.gson.annotations.SerializedName

data class Level(

    @SerializedName("value") val value: Int,
    @SerializedName("display_string") val display_string: String
)