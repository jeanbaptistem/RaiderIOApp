package fr.jbme.raiderioapp.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName

data class Durability(

    @SerializedName("value") val value: Int,
    @SerializedName("display_string") val display_string: String
)