package fr.jbme.raiderioapp.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName

data class Armor(

    @SerializedName("value") val value: Int,
    @SerializedName("display") val display: Display
)