package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class HonorableKills(

    @SerializedName("tier") val tier: Int,
    @SerializedName("value") val value: Int
)