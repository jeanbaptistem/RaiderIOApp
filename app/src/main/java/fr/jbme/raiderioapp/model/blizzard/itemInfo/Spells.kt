package fr.jbme.raiderioapp.model.blizzard.itemInfo

import com.google.gson.annotations.SerializedName


data class Spells(

    @SerializedName("spell") val spell: Spell,
    @SerializedName("description") val description: String
)