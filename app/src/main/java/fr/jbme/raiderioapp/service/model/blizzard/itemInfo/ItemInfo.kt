package fr.jbme.raiderioapp.service.model.blizzard.itemInfo

import com.google.gson.annotations.SerializedName

data class ItemInfo(

    @SerializedName("fr.jbme.raiderioapp.service.model.blizzard.dungeonInfo._links") val _links: _links,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("quality") val quality: Quality,
    @SerializedName("level") val level: Int,
    @SerializedName("required_level") val required_level: Int,
    @SerializedName("media") val media: Media,
    @SerializedName("item_class") val item_class: ItemClass,
    @SerializedName("item_subclass") val item_subclass: ItemSubclass,
    @SerializedName("inventory_type") val inventory_type: InventoryType,
    @SerializedName("purchase_price") val purchase_price: Int,
    @SerializedName("sell_price") val sell_price: Int,
    @SerializedName("max_count") val max_count: Int,
    @SerializedName("is_equippable") val is_equippable: Boolean,
    @SerializedName("is_stackable") val is_stackable: Boolean,
    @SerializedName("preview_item") val preview_item: PreviewItem
)