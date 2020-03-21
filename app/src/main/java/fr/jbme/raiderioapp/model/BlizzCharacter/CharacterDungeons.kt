package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class CharacterDungeons(

    @SerializedName("completed") val completed: String,
    @SerializedName("characterDungeon") val characterDungeon: CharacterDungeon,
    @SerializedName("duration") val duration: Int,
    @SerializedName("level") val level: Int,
    @SerializedName("modifiers") val modifiers: List<Modifiers>,
    @SerializedName("party") val party: List<Party>,
    @SerializedName("qualifyingDuration") val qualifyingDuration: Int,
    @SerializedName("withinTime") val withinTime: Boolean,
    @SerializedName("time") val time: String,
    @SerializedName("id") val id: String
)