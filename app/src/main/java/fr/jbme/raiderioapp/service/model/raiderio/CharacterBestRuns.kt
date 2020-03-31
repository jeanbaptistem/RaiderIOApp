package fr.jbme.raiderioapp.service.model.raiderio


import com.google.gson.annotations.SerializedName

data class CharacterBestRuns(
    @SerializedName("achievement_points")
    val achievementPoints: Int?,
    @SerializedName("active_spec_name")
    val activeSpecName: String?,
    @SerializedName("active_spec_role")
    val activeSpecRole: String?,
    @SerializedName("class")
    val classX: String?,
    @SerializedName("faction")
    val faction: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("honorable_kills")
    val honorableKills: Int?,
    @SerializedName("mythic_plus_best_runs")
    val mythicPlusBestRuns: List<MythicPlusBestRun?>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("profile_banner")
    val profileBanner: String?,
    @SerializedName("profile_url")
    val profileUrl: String?,
    @SerializedName("race")
    val race: String?,
    @SerializedName("realm")
    val realm: String?,
    @SerializedName("region")
    val region: String?,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String?
) {
    data class MythicPlusBestRun(
        @SerializedName("affixes")
        val affixes: List<Affixe?>?,
        @SerializedName("clear_time_ms")
        val clearTimeMs: Long?,
        @SerializedName("completed_at")
        val completedAt: String?,
        @SerializedName("dungeon")
        val dungeon: String?,
        @SerializedName("map_challenge_mode_id")
        val mapChallengeModeId: Int?,
        @SerializedName("mythic_level")
        val mythicLevel: Int?,
        @SerializedName("num_keystone_upgrades")
        val numKeystoneUpgrades: Int?,
        @SerializedName("score")
        val score: Double?,
        @SerializedName("short_name")
        val shortName: String?,
        @SerializedName("url")
        val url: String?
    ) {
        data class Affixe(
            @SerializedName("description")
            val description: String?,
            @SerializedName("id")
            val id: Int?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("wowhead_url")
            val wowheadUrl: String?
        )
    }
}