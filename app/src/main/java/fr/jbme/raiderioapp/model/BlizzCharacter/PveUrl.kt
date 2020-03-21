package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName

data class PveUrl(
    @SerializedName("mythic") val mythic: String,
    @SerializedName("raids") val raids: String
)