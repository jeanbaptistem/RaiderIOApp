package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName

data class PveUrl(
    @SerializedName("mythic") val mythic: String,
    @SerializedName("raids") val raids: String
)