package fr.jbme.raiderioapp.data.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SellPrice(
    @SerializedName("value")
    @Expose
    var value: Int? = null,

    @SerializedName("display_strings")
    @Expose
    var displayStrings: DisplayStrings? = null
)