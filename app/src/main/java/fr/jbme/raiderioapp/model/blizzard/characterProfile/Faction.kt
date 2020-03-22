package fr.jbme.raiderioapp.model.blizzard.characterProfile

import com.google.gson.annotations.SerializedName

data class Faction(

    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String
)