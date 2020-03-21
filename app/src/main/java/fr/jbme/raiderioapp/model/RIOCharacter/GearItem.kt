package fr.jbme.raiderioapp.model.RIOCharacter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GearItem(
    @SerializedName("item_id")
    @Expose
    var itemId: Int? = null,

    @SerializedName("item_level")
    @Expose
    var itemLevel: Int? = null,

    @SerializedName("item_quality")
    @Expose
    var itemQuality: Int? = null,

    @SerializedName("is_legion_legendary")
    @Expose
    var isLegionLegendary: Boolean? = null,

    @SerializedName("is_azerite_armor")
    @Expose
    var isAzeriteArmor: Boolean? = null,

    @SerializedName("azerite_powers")
    @Expose
    var azeritePowers: List<AzeritePower>? = null,

    @SerializedName("corruption")
    @Expose
    var corruption: Corruption? = null,

    @SerializedName("gems")
    @Expose
    var gems: List<Int>? = null,

    @SerializedName("bonuses")
    @Expose
    var bonuses: List<Int>? = null
)