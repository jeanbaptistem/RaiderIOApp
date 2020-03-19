package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Spells(

    @SerializedName("description") val description: String,
    @SerializedName("color") val color: Color,
    @SerializedName("spell") val spell: Spell
)