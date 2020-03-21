package fr.jbme.raiderioapp.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Requirements(
    @SerializedName("level")
    @Expose
    var level: Level? = null
)