package fr.jbme.raiderioapp.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Dps(
    @SerializedName("value")
    @Expose
    var value: Float? = null,

    @SerializedName("display_string")
    @Expose
    var displayString: String? = null
)