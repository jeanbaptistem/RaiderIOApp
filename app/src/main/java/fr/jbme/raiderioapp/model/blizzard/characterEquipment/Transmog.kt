package fr.jbme.raiderioapp.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName


data class Transmog(

    @SerializedName("item") val item: Item,
    @SerializedName("display_string") val display_string: String,
    @SerializedName("item_modified_appearance_id") val item_modified_appearance_id: Int
)