package fr.jbme.raiderioapp.service.model.raiderio

import com.google.gson.annotations.SerializedName

data class RaidProgression(

    @SerializedName("battle-of-dazaralor") val battle_of_dazaralor: Raid,
    @SerializedName("crucible-of-storms") val crucible_of_storms: Raid,
    @SerializedName("nyalotha-the-waking-city") val nyalotha_the_waking_city: Raid,
    @SerializedName("the-eternal-palace") val the_eternal_palace: Raid,
    @SerializedName("uldir") val uldir: Raid
)