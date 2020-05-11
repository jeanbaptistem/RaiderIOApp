package fr.jbme.raiderioapp.service.model.raiderio


import com.google.gson.annotations.SerializedName

data class CharacterRanks(
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
    @SerializedName("mythic_plus_ranks")
    val mythicPlusRanks: List<Rank>?,
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
    data class Rank(
        val name: String?,
        @SerializedName("realm")
        val realm: Int?,
        @SerializedName("region")
        val region: Int?,
        @SerializedName("world")
        val world: Int?
    )
}