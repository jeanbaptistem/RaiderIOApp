package fr.jbme.raiderioapp.data.model.character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RIOCharacterResponse(
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("race")
    @Expose
    var race: String? = null,

    @SerializedName("class")
    @Expose
    var _class: String? = null,

    @SerializedName("active_spec_name")
    @Expose
    var activeSpecName: String? = null,

    @SerializedName("active_spec_role")
    @Expose
    var activeSpecRole: String? = null,

    @SerializedName("gender")
    @Expose
    var gender: String? = null,

    @SerializedName("faction")
    @Expose
    var faction: String? = null,

    @SerializedName("achievement_points")
    @Expose
    var achievementPoints: Int? = null,

    @SerializedName("honorable_kills")
    @Expose
    var honorableKills: Int? = null,

    @SerializedName("thumbnail_url")
    @Expose
    var thumbnailUrl: String? = null,

    @SerializedName("region")
    @Expose
    var region: String? = null,

    @SerializedName("realm")
    @Expose
    var realm: String? = null,

    @SerializedName("profile_url")
    @Expose
    var profileUrl: String? = null,

    @SerializedName("profile_banner")
    @Expose
    var profileBanner: String? = null,

    @SerializedName("mythic_plus_ranks")
    @Expose
    var mythicPlusRanks: MythicPlusRanks? = null,

    @SerializedName("mythic_plus_best_runs")
    @Expose
    var mythicPlusBestRuns: List<MythicPlusBestRun>? = null,

    @SerializedName("gear")
    @Expose
    var gear: Gear? = null,

    @SerializedName("raid_progression")
    @Expose
    var raidProgression: RaidProgression? = null,

    @SerializedName("guild")
    @Expose
    var guild: Guild? = null
)