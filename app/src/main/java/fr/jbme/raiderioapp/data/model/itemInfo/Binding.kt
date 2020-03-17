package fr.jbme.raiderioapp.data.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Binding(
    @SerializedName("type")
    @Expose
    var type: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null
)