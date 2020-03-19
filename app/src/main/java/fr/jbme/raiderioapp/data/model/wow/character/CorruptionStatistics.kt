package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class CorruptionStatistics(

    @SerializedName("corruption") val corruption: Int,
    @SerializedName("corruptionResistance") val corruptionResistance: Int,
    @SerializedName("effectiveCorruption") val effectiveCorruption: Int
)