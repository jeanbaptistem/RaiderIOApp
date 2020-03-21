package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Prestige(

    @SerializedName("honorLevel") val honorLevel: Int
)