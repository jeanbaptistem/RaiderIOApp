package fr.jbme.raiderioapp.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName


data class SpellTooltip(

    @SerializedName("spell") val spell: Spell,
    @SerializedName("description") val description: String,
    @SerializedName("cast_time") val cast_time: String,
    @SerializedName("cooldown") val cooldown: String
)