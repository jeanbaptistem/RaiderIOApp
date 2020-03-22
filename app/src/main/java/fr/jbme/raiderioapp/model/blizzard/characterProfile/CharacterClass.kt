package fr.jbme.raiderioapp.model.blizzard.characterProfile

import com.google.gson.annotations.SerializedName

data class CharacterClass(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int
)