package fr.jbme.raiderioapp.service.model.blizzard.dungeonInfo

import com.google.gson.annotations.SerializedName

data class BestRuns(

    @SerializedName("completed_timestamp") val completed_timestamp: Long,
    @SerializedName("duration") val duration: Long,
    @SerializedName("keystone_level") val keystone_level: Int,
    @SerializedName("keystone_affixes") val keystone_affixes: List<KeystoneAffixes>,
    @SerializedName("members") val members: List<Members>,
    @SerializedName("dungeon") val dungeon: Dungeon,
    @SerializedName("is_completed_within_time") val is_completed_within_time: Boolean
)