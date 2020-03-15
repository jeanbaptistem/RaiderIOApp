package fr.jbme.raiderioapp.data.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("self")
    @Expose
    var self: Self? = null
)