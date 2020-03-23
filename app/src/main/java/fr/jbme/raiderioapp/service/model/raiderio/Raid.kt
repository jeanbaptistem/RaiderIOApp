package fr.jbme.raiderioapp.service.model.raiderio

import com.google.gson.annotations.SerializedName

data class Raid(

    @SerializedName("summary") val summary: String,
    @SerializedName("total_bosses") val total_bosses: Int,
    @SerializedName("normal_bosses_killed") val normal_bosses_killed: Int,
    @SerializedName("heroic_bosses_killed") val heroic_bosses_killed: Int,
    @SerializedName("mythic_bosses_killed") val mythic_bosses_killed: Int
)