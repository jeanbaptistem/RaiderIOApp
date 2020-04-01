package fr.jbme.raiderioapp.service.model.raiderio


import com.google.gson.annotations.SerializedName

data class CharacterScore(
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
    @SerializedName("mythic_plus_scores_by_season")
    val mythicPlusScoresBySeason: List<MythicPlusScoresBySeason?>?,
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
    data class MythicPlusScoresBySeason(
        @SerializedName("scores")
        val scores: Scores?,
        @SerializedName("season")
        val season: String?
    ) {
        data class Scores(
            @SerializedName("all")
            val all: Double?,
            @SerializedName("dps")
            val dps: Double?,
            @SerializedName("healer")
            val healer: Double?,
            @SerializedName("spec_0")
            val spec0: Double?,
            @SerializedName("spec_1")
            val spec1: Double?,
            @SerializedName("spec_2")
            val spec2: Double?,
            @SerializedName("spec_3")
            val spec3: Double?,
            @SerializedName("tank")
            val tank: Double?
        )
    }
}