package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class SelectedPowers(

    @SerializedName("id") val id: Int,
    @SerializedName("is_display_hidden") val is_display_hidden: Boolean,
    @SerializedName("spell_tooltip") val spell_tooltip: SpellTooltip,
    @SerializedName("tier") val tier: Int
)