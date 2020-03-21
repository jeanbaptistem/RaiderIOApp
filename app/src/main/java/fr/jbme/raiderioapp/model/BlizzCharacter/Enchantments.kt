package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Enchantments(

    @SerializedName("display_string") val display_string: String,
    @SerializedName("enchantment_id") val enchantment_id: Int,
    @SerializedName("enchantment_slot") val enchantment_slot: EnchantmentSlot,
    @SerializedName("source_item") val source_item: SourceItem
)