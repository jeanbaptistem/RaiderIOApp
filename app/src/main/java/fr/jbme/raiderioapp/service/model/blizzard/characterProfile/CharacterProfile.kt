package fr.jbme.raiderioapp.service.model.blizzard.characterProfile

import com.google.gson.annotations.SerializedName

data class CharacterProfile(

    @SerializedName("fr.jbme.raiderioapp.service.model.blizzard.dungeonInfo._links") val _links: _links,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("gender") val gender: Gender,
    @SerializedName("faction") val faction: Faction,
    @SerializedName("race") val race: Race,
    @SerializedName("character_class") val character_class: CharacterClass,
    @SerializedName("active_spec") val active_spec: ActiveSpec,
    @SerializedName("realm") val realm: Realm,
    @SerializedName("guild") val guild: Guild,
    @SerializedName("level") val level: Int,
    @SerializedName("experience") val experience: Int,
    @SerializedName("achievement_points") val achievement_points: Int,
    @SerializedName("achievements") val achievements: Achievements,
    @SerializedName("titles") val titles: Titles,
    @SerializedName("pvp_summary") val pvp_summary: PvpSummary,
    @SerializedName("encounters") val encounters: Encounters,
    @SerializedName("media") val media: Media,
    @SerializedName("last_login_timestamp") val last_login_timestamp: Long,
    @SerializedName("average_item_level") val average_item_level: Int,
    @SerializedName("equipped_item_level") val equipped_item_level: Int,
    @SerializedName("specializations") val specializations: Specializations,
    @SerializedName("statistics") val statistics: Statistics,
    @SerializedName("mythic_keystoneProfile") val mythic_keystoneProfile: MythicKeystoneProfile,
    @SerializedName("equipment") val equipment: Equipment,
    @SerializedName("appearance") val appearance: Appearance,
    @SerializedName("collections") val collections: Collections,
    @SerializedName("active_title") val active_title: ActiveTitle,
    @SerializedName("reputations") val reputations: Reputations,
    @SerializedName("quests") val quests: Quests,
    @SerializedName("achievements_statistics") val achievements_statistics: AchievementsStatistics
)