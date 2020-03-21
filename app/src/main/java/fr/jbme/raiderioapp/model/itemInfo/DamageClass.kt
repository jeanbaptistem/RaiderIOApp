package fr.jbme.raiderioapp.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DamageClass(
    @SerializedName("type")
    @Expose
    var type: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null
)