package fr.jbme.raiderioapp.model.blizzard.itemInfo

import com.google.gson.annotations.SerializedName


data class Stats(

    @SerializedName("type") val type: Type,
    @SerializedName("value") val value: Int,
    @SerializedName("is_negated") val is_negated: Boolean,
    @SerializedName("display") val display: Display
)