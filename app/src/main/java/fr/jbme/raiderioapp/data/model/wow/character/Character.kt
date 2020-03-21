package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Character(

    @SerializedName("achievement") val achievement: Int,
    @SerializedName("avatar") val avatar: Avatar,
    @SerializedName("averageItemLevel") val averageItemLevel: Int,
    @SerializedName("bust") val bust: Bust,
    @SerializedName("class") val _class: Class_,
    @SerializedName("corruptionStatistics") val corruptionStatistics: CorruptionStatistics,
    @SerializedName("faction") val faction: Faction,
    @SerializedName("gear") val gear: Gear,
    @SerializedName("gender") val gender: Gender,
    @SerializedName("guild") val guild: Guild,
    @SerializedName("level") val level: Int,
    @SerializedName("characterMythicKeystoneDungeons") val characterMythicKeystoneDungeons: List<CharacterMythicKeystoneDungeons>,
    @SerializedName("name") val name: String,
    @SerializedName("pve") val pve: Pve,
    @SerializedName("pvp") val pvp: Pvp,
    @SerializedName("race") val race: Race,
    @SerializedName("realm") val realm: Realm,
    @SerializedName("region") val region: String,
    @SerializedName("render") val render: Render,
    @SerializedName("spec") val spec: Spec,
    @SerializedName("specs") val specs: List<Specs>,
    @SerializedName("stats") val stats: CharacterStats,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("achievementUrl") val achievementUrl: String,
    @SerializedName("prefix") val prefix: String?,
    @SerializedName("suffix") val suffix: String?
)