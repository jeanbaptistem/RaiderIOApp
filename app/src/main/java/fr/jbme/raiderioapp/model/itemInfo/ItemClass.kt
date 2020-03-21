package fr.jbme.raiderioapp.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ItemClass(
    @SerializedName("key")
    @Expose
    var key: Key? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("id")
    @Expose
    var id: Int? = null
)