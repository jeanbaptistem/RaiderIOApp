package fr.jbme.raiderioapp.service.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName

data class Type(

    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String
)