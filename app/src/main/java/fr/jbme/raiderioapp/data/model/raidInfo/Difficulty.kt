package fr.jbme.raiderioapp.data.model.raidInfo

import com.google.gson.annotations.SerializedName

data class Difficulty(

    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String
)