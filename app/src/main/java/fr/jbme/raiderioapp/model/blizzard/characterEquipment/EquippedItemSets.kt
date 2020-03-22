package fr.jbme.raiderioapp.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName


data class EquippedItemSets(

    @SerializedName("item_set") val item_set: ItemSet,
    @SerializedName("items") val items: List<Items>,
    @SerializedName("effects") val effects: List<Effects>,
    @SerializedName("display_string") val display_string: String
)