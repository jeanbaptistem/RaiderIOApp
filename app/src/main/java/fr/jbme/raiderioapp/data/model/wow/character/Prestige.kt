package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Prestige(

    @SerializedName("honorLevel") val honorLevel: Int
)