package fr.jbme.raiderioapp.data.model.character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Gear(
    @SerializedName("item_level_equipped")
    @Expose
    var itemLevelEquipped: Int? = null,

    @SerializedName("item_level_total")
    @Expose
    var itemLevelTotal: Int? = null,

    @SerializedName("artifact_traits")
    @Expose
    var artifactTraits: Float? = null,

    @SerializedName("corruption")
    @Expose
    var corruption: Corruption? = null,

    @SerializedName("items")
    @Expose
    var items: Items? = null
)