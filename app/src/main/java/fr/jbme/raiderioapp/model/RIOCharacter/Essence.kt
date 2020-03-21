package fr.jbme.raiderioapp.model.RIOCharacter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Essence(
    @SerializedName("slot")
    @Expose
    var slot: Int? = null,

    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("rank")
    @Expose
    var rank: Int? = null,

    @SerializedName("power")
    @Expose
    var power: Power? = null
)