package fr.jbme.raiderioapp.service.model.blizzard.dungeonInfo

import com.google.gson.annotations.SerializedName

data class DungeonInfo(

    @SerializedName("_links") val _links: _links,
    @SerializedName("season") val season: Season,
    @SerializedName("best_runs") val best_runs: List<BestRuns>,
    @SerializedName("character") val character: Character
)