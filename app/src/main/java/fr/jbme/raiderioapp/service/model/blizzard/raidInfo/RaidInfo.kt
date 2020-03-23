package fr.jbme.raiderioapp.service.model.blizzard.raidInfo

import com.google.gson.annotations.SerializedName

data class RaidInfo(

    @SerializedName("_links") val Links: Links,
    @SerializedName("character") val character: Character,
    @SerializedName("expansions") val expansions: List<Expansions>
)