package fr.jbme.raiderioapp.service.model.blizzard.itemInfo

import com.google.gson.annotations.SerializedName


data class PreviewItem(

    @SerializedName("item") val item: Item,
    @SerializedName("quality") val quality: Quality,
    @SerializedName("name") val name: String,
    @SerializedName("media") val media: Media,
    @SerializedName("item_class") val item_class: ItemClass,
    @SerializedName("item_subclass") val item_subclass: ItemSubclass,
    @SerializedName("inventory_type") val inventory_type: InventoryType,
    @SerializedName("binding") val binding: Binding,
    @SerializedName("unique_equipped") val unique_equipped: String,
    @SerializedName("weapon") val weapon: Weapon,
    @SerializedName("stats") val stats: List<Stats>,
    @SerializedName("spells") val spells: List<Spells>,
    @SerializedName("sell_price") val sell_price: SellPrice,
    @SerializedName("requirements") val requirements: Requirements,
    @SerializedName("level") val level: Level,
    @SerializedName("durability") val durability: Durability
)