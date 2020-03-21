package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class CorruptionStatistics(

    @SerializedName("corruption") val corruption: Int,
    @SerializedName("corruptionResistance") val corruptionResistance: Int,
    @SerializedName("effectiveCorruption") val effectiveCorruption: Int
)