package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class SelectedEssences(

    @SerializedName("essence") val essence: Essence,
    @SerializedName("main_spell_tooltip") val main_spell_tooltip: MainSpellTooltip,
    @SerializedName("media") val media: Media,
    @SerializedName("passive_spell_tooltip") val passive_spell_tooltip: PassiveSpellTooltip,
    @SerializedName("rank") val rank: Int,
    @SerializedName("slot") val slot: Int
)