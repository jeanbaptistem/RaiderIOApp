package fr.jbme.raiderioapp.service.model.raiderio.dungeonsBestRuns

import com.google.gson.annotations.SerializedName

data class Affixes(

    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("wowhead_url") val wowhead_url: String
)