package fr.jbme.raiderioapp.data.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Requirements(
    @SerializedName("level")
    @Expose
    var level: Level? = null
)