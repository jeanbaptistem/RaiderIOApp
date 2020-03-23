package fr.jbme.raiderioapp.service.model.blizzard.itemInfo

import com.google.gson.annotations.SerializedName

data class DisplayStrings(

    @SerializedName("header") val header: String,
    @SerializedName("gold") val gold: Int,
    @SerializedName("silver") val silver: Int,
    @SerializedName("copper") val copper: Int
)