package fr.jbme.raiderioapp.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Display(
    @SerializedName("display_string")
    @Expose
    var displayString: String? = null,

    @SerializedName("color")
    @Expose
    var color: Color? = null
)