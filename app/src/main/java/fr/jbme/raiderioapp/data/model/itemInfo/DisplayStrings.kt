package fr.jbme.raiderioapp.data.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DisplayStrings(
    @SerializedName("header")
    @Expose
    var header: String? = null,

    @SerializedName("gold")
    @Expose
    var gold: String? = null,

    @SerializedName("silver")
    @Expose
    var silver: String? = null,

    @SerializedName("copper")
    @Expose
    var copper: String? = null
)