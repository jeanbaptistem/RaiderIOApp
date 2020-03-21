package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Finger(

    @SerializedName("binding") val binding: Binding,
    @SerializedName("bonus_list") val bonus_list: List<Int>,
    @SerializedName("context") val context: Int,
    @SerializedName("enchantments") val enchantments: List<Enchantments>,
    @SerializedName("id") val id: Int,
    @SerializedName("inventory_type") val inventory_type: InventoryType,
    @SerializedName("is_subclass_hidden") val is_subclass_hidden: Boolean,
    @SerializedName("item") val item: Item,
    @SerializedName("item_class") val item_class: ItemClass,
    @SerializedName("item_subclass") val item_subclass: ItemSubclass,
    @SerializedName("level") val level: Level,
    @SerializedName("media") val media: Media,
    @SerializedName("modified_appearance_id") val modified_appearance_id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("name_description") val name_description: NameDescription,
    @SerializedName("quality") val quality: Quality,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("requirements") val requirements: Requirements,
    @SerializedName("sell_price") val sell_price: SellPrice,
    @SerializedName("slot") val slot: Slot,
    @SerializedName("stats") val stats: List<GearStats>,
    @SerializedName("unique_equipped") val unique_equipped: String
)