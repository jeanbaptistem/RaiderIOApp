package fr.jbme.raiderioapp.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName

data class CharacterEquipment(

    @SerializedName("fr.jbme.raiderioapp.model.blizzard.itemInfo.fr.jbme.raiderioapp.model.blizzard.itemMedia._links") val _links: _links,
    @SerializedName("character") val character: Character,
    @SerializedName("equipped_items") val equipped_items: List<EquippedItems>,
    @SerializedName("equipped_item_sets") val equipped_item_sets: List<EquippedItemSets>
)