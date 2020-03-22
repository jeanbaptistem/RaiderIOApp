package fr.jbme.raiderioapp.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName


data class EquippedItems(

    @SerializedName("item") val item: Item,
    @SerializedName("slot") val slot: Slot,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("context") val context: Int,
    @SerializedName("bonus_list") val bonus_list: List<Int>,
    @SerializedName("quality") val quality: Quality,
    @SerializedName("name") val name: String,
    @SerializedName("modified_appearance_id") val modified_appearance_id: Int,
    @SerializedName("azerite_details") val azerite_details: AzeriteDetails?,
    @SerializedName("media") val media: Media,
    @SerializedName("item_class") val item_class: ItemClass,
    @SerializedName("item_subclass") val item_subclass: ItemSubclass,
    @SerializedName("inventory_type") val inventory_type: InventoryType,
    @SerializedName("binding") val binding: Binding,
    @SerializedName("armor") val armor: Armor,
    @SerializedName("stats") val stats: List<Stats>,
    @SerializedName("requirements") val requirements: Requirements,
    @SerializedName("level") val level: Level,
    @SerializedName("transmog") val transmog: Transmog,
    @SerializedName("durability") val durability: Durability,
    @SerializedName("name_description") val name_description: NameDescription
)