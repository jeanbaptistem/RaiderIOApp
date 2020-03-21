package fr.jbme.raiderioapp.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Level(
    @SerializedName("value")
    @Expose
    var value: Int? = null,

    @SerializedName("display_string")
    @Expose
    var displayString: String? = null
)