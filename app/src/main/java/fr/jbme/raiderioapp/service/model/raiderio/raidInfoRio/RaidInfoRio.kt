package fr.jbme.raiderioapp.service.model.raiderio.raidInfoRio

import com.google.gson.annotations.SerializedName

data class RaidInfoRio(

    @SerializedName("name") val name: String,
    @SerializedName("race") val race: String,
    @SerializedName("class") val _class: String,
    @SerializedName("active_spec_name") val active_spec_name: String,
    @SerializedName("active_spec_role") val active_spec_role: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("faction") val faction: String,
    @SerializedName("achievement_points") val achievement_points: Int,
    @SerializedName("honorable_kills") val honorable_kills: Int,
    @SerializedName("thumbnail_url") val thumbnail_url: String,
    @SerializedName("region") val region: String,
    @SerializedName("realm") val realm: String,
    @SerializedName("profile_url") val profile_url: String,
    @SerializedName("profile_banner") val profile_banner: String,
    @SerializedName("raid_progression") val raid_progression: RaidProgression
)