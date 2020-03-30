package fr.jbme.raiderioapp.service.model.raiderio.dungeonsBestRuns

import com.google.gson.annotations.SerializedName

data class MythicPlusBestRuns(

    @SerializedName("dungeon") val dungeon: String,
    @SerializedName("short_name") val short_name: String,
    @SerializedName("mythic_level") val mythic_level: Int,
    @SerializedName("completed_at") val completed_at: String,
    @SerializedName("clear_time_ms") val clear_time_ms: Long,
    @SerializedName("num_keystone_upgrades") val num_keystone_upgrades: Int,
    @SerializedName("map_challenge_mode_id") val map_challenge_mode_id: Int,
    @SerializedName("score") val score: Double,
    @SerializedName("affixes") val affixes: List<Affixes>,
    @SerializedName("url") val url: String
)