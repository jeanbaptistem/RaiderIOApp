package fr.jbme.raiderioapp.service.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName

data class Armor(

    @SerializedName("value") val value: Int,
    @SerializedName("display") val display: Display
)