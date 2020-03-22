package fr.jbme.raiderioapp.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName

data class InventoryType(

    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String
)