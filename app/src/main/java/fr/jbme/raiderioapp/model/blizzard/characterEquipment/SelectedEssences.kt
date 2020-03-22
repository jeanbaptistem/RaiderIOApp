package fr.jbme.raiderioapp.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName

data class SelectedEssences(
    @SerializedName("slot") val slot: Int,
    @SerializedName("rank") val rank: Int,
    @SerializedName("main_spell_tooltip") val main_spell_tooltip: SpellTooltip?,
    @SerializedName("passive_spell_tooltip") val passive_spell_tooltip: SpellTooltip,
    @SerializedName("essence") val essence: Essence,
    @SerializedName("media") val media: Media
)
