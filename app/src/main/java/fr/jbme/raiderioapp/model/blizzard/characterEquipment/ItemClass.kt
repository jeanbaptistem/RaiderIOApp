package fr.jbme.raiderioapp.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName


data class ItemClass(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int
)