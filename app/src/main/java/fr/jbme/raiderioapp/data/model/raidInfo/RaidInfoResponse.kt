package fr.jbme.raiderioapp.data.model.raidInfo

import com.google.gson.annotations.SerializedName

data class RaidInfoResponse(

    @SerializedName("_links") val Links: Links,
    @SerializedName("character") val character: Character,
    @SerializedName("expansions") val expansions: List<Expansions>
)