package fr.jbme.raiderioapp.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ItemInfoResponse(
    @SerializedName("_links")
    @Expose
    var links: Links? = null,

    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("quality")
    @Expose
    var quality: Quality? = null,

    @SerializedName("level")
    @Expose
    var level: Int? = null,

    @SerializedName("required_level")
    @Expose
    var requiredLevel: Int? = null,

    @SerializedName("media")
    @Expose
    var media: Media? = null,

    @SerializedName("item_class")
    @Expose
    var itemClass: ItemClass? = null,

    @SerializedName("item_subclass")
    @Expose
    var itemSubclass: ItemSubclass? = null,

    @SerializedName("inventory_type")
    @Expose
    var inventoryType: InventoryType? = null,

    @SerializedName("purchase_price")
    @Expose
    var purchasePrice: Int? = null,

    @SerializedName("sell_price")
    @Expose
    var sellPrice: Int? = null,

    @SerializedName("max_count")
    @Expose
    var maxCount: Int? = null,

    @SerializedName("is_equippable")
    @Expose
    var isEquippable: Boolean? = null,

    @SerializedName("is_stackable")
    @Expose
    var isStackable: Boolean? = null,

    @SerializedName("preview_item")
    @Expose
    var previewItem: PreviewItem? = null
)