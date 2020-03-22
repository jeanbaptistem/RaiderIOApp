package fr.jbme.raiderioapp.model.blizzard.profileInfo

import com.google.gson.annotations.SerializedName

data class Characters(

    @SerializedName("character") val character: Character,
    @SerializedName("protected_character") val protected_character: ProtectedCharacter,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("realm") val realm: Realm,
    @SerializedName("playable_class") val playable_class: PlayableClass,
    @SerializedName("playable_race") val playable_race: PlayableRace,
    @SerializedName("gender") val gender: Gender,
    @SerializedName("faction") val faction: Faction,
    @SerializedName("level") val level: Int
)