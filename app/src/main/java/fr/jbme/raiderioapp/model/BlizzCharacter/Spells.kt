package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Spells(

    @SerializedName("description") val description: String,
    @SerializedName("color") val color: Color,
    @SerializedName("spell") val spell: Spell
)