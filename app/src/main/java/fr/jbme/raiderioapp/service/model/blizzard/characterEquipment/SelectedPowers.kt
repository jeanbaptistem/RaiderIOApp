package fr.jbme.raiderioapp.service.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName

data class SelectedPowers(

    @SerializedName("id") val id: Int,
    @SerializedName("tier") val tier: Int,
    @SerializedName("spell_tooltip") val spell_tooltip: SpellTooltip,
    @SerializedName("is_display_hidden") val is_display_hidden: Boolean
)