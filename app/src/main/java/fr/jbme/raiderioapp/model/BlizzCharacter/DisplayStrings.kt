package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class DisplayStrings(

    @SerializedName("copper") val copper: Int,
    @SerializedName("gold") val gold: Int,
    @SerializedName("header") val header: String,
    @SerializedName("silver") val silver: Int
)