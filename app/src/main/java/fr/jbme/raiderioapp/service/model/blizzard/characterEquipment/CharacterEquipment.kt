package fr.jbme.raiderioapp.service.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName

data class CharacterEquipment(

    @SerializedName("_links") val _links: _links,
    @SerializedName("character") val character: Character,
    @SerializedName("equipped_items") val equipped_items: List<EquippedItems>,
    @SerializedName("equipped_item_sets") val equipped_item_sets: List<EquippedItemSets>
)