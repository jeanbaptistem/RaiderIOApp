package fr.jbme.raiderioapp.data.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("key")
    @Expose
    var key: Key? = null,

    @SerializedName("id")
    @Expose
    var id: Int? = null
)