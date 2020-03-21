package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class PassiveSpellTooltip(

    @SerializedName("cast_time") val cast_time: String,
    @SerializedName("description") val description: String,
    @SerializedName("spell") val spell: Spell
)