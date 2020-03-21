package fr.jbme.raiderioapp.model.RIOCharacter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MythicPlusBestRun(
    @SerializedName("characterDungeon")
    @Expose
    var dungeon: String? = null,

    @SerializedName("short_name")
    @Expose
    var shortName: String? = null,

    @SerializedName("mythic_level")
    @Expose
    var mythicLevel: Int? = null,

    @SerializedName("completed_at")
    @Expose
    var completedAt: String? = null,

    @SerializedName("clear_time_ms")
    @Expose
    var clearTimeMs: Int? = null,

    @SerializedName("num_keystone_upgrades")
    @Expose
    var numKeystoneUpgrades: Int? = null,

    @SerializedName("map_challenge_mode_id")
    @Expose
    var mapChallengeModeId: Int? = null,

    @SerializedName("score")
    @Expose
    var score: Float? = null,

    @SerializedName("affixes")
    @Expose
    var affixes: List<Affix>? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null
)