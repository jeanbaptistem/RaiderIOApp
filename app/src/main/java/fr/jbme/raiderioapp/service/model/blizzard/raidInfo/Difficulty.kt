package fr.jbme.raiderioapp.service.model.blizzard.raidInfo

import com.google.gson.annotations.SerializedName

data class Difficulty(

    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String
)