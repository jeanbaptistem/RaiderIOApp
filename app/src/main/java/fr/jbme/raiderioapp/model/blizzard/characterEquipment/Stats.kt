package fr.jbme.raiderioapp.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName


data class Stats(

    @SerializedName("type") val type: Type,
    @SerializedName("value") val value: Int,
    @SerializedName("display") val display: Display
)