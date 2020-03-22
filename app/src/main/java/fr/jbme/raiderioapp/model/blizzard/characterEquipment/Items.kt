package fr.jbme.raiderioapp.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName


data class Items(

    @SerializedName("item") val item: Item,
    @SerializedName("is_equipped") val is_equipped: Boolean
)