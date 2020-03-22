package fr.jbme.raiderioapp.model.blizzard.profileInfo

import com.google.gson.annotations.SerializedName

data class Faction(

    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String
)