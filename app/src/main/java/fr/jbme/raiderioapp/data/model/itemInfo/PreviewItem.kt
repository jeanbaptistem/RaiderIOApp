package fr.jbme.raiderioapp.data.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PreviewItem(
    @SerializedName("item")
    @Expose
    var item: Item? = null,

    @SerializedName("quality")
    @Expose
    var quality: Quality? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

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

    @SerializedName("binding")
    @Expose
    var binding: Binding? = null,

    @SerializedName("weapon")
    @Expose
    var weapon: Weapon? = null,

    @SerializedName("stats")
    @Expose
    var stats: List<Stat>? = null,

    @SerializedName("sell_price")
    @Expose
    var sellPrice: SellPrice? = null,

    @SerializedName("requirements")
    @Expose
    var requirements: Requirements? = null,

    @SerializedName("level")
    @Expose
    var level: Level? = null,

    @SerializedName("durability")
    @Expose
    var durability: Durability? = null
)