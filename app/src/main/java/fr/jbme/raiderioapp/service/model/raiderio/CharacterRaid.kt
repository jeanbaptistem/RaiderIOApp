package fr.jbme.raiderioapp.service.model.raiderio


import com.google.gson.annotations.SerializedName

data class CharacterRaid(
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
    @SerializedName("name")
    val name: String?,
    @SerializedName("profile_banner")
    val profileBanner: String?,
    @SerializedName("profile_url")
    val profileUrl: String?,
    @SerializedName("race")
    val race: String?,
    @SerializedName("raid_progression")
    val raidProgression: RaidProgression?,
    @SerializedName("realm")
    val realm: String?,
    @SerializedName("region")
    val region: String?,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String?
) {
    data class RaidProgression(
        @SerializedName("battle-of-dazaralor")
        val battleOfDazaralor: Raid?,
        @SerializedName("crucible-of-storms")
        val crucibleOfStorms: Raid?,
        @SerializedName("nyalotha-the-waking-city")
        val nyalothaTheWakingCity: Raid?,
        @SerializedName("the-eternal-palace")
        val theEternalPalace: Raid?,
        @SerializedName("uldir")
        val uldir: Raid?
    ) {
        data class Raid(
            @SerializedName("heroic_bosses_killed")
            val heroicBossesKilled: Int?,
            @SerializedName("mythic_bosses_killed")
            val mythicBossesKilled: Int?,
            @SerializedName("normal_bosses_killed")
            val normalBossesKilled: Int?,
            @SerializedName("summary")
            val summary: String?,
            @SerializedName("total_bosses")
            val totalBosses: Int?
        )
    }
}