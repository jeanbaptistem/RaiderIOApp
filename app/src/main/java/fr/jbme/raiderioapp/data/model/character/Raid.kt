package fr.jbme.raiderioapp.data.model.character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Raid(
    @SerializedName("summary")
    @Expose
    var summary: String? = null,

    @SerializedName("total_bosses")
    @Expose
    var totalBosses: Int? = null,

    @SerializedName("normal_bosses_killed")
    @Expose
    var normalBossesKilled: Int? = null,

    @SerializedName("heroic_bosses_killed")
    @Expose
    var heroicBossesKilled: Int? = null,

    @SerializedName("mythic_bosses_killed")
    @Expose
    var mythicBossesKilled: Int? = null
)