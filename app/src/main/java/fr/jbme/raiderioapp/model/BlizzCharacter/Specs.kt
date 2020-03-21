package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Specs(

    @SerializedName("active") val active: Boolean,
    @SerializedName("description") val description: String,
    @SerializedName("glyphs") val glyphs: List<String>,
    @SerializedName("icon") val icon: Icon,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("pvpTalents") val pvpTalents: List<PvpTalents>,
    @SerializedName("talents") val talents: List<Talents>,
    @SerializedName("url") val url: String
)