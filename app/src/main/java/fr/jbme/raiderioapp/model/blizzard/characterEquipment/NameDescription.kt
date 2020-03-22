package fr.jbme.raiderioapp.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName

data class NameDescription(

    @SerializedName("display_string") val display_string: String,
    @SerializedName("color") val color: Color
)