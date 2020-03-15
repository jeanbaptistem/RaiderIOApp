package fr.jbme.raiderioapp.data.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Durability(
    @SerializedName("value")
    @Expose
    var value: Int? = null,

    @SerializedName("display_string")
    @Expose
    var displayString: String? = null
)