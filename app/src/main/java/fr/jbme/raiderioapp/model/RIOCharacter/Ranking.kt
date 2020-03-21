package fr.jbme.raiderioapp.model.RIOCharacter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Ranking(
    @SerializedName("world")
    @Expose
    var world: Int? = null,

    @SerializedName("region")
    @Expose
    var region: Int? = null,

    @SerializedName("realm")
    @Expose
    var realm: Int? = null
)