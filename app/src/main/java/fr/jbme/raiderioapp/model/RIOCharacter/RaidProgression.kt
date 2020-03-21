package fr.jbme.raiderioapp.model.RIOCharacter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RaidProgression(
    @SerializedName("battle-of-dazaralor")
    @Expose
    var battleOfDazaralor: Raid? = null,

    @SerializedName("crucible-of-storms")
    @Expose
    var crucibleOfStorms: Raid? = null,

    @SerializedName("nyalotha-the-waking-city")
    @Expose
    var nyalothaTheWakingCity: Raid? = null,

    @SerializedName("the-eternal-palace")
    @Expose
    var theEternalPalace: Raid? = null,

    @SerializedName("uldir")
    @Expose
    var uldir: Raid? = null
)