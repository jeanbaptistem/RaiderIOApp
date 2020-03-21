package fr.jbme.raiderioapp.model.raidInfo

import com.google.gson.annotations.SerializedName

data class RaidInfoResponse(

    @SerializedName("_links") val Links: Links,
    @SerializedName("RIOCharacter") val character: Character,
    @SerializedName("expansions") val expansions: List<Expansions>
)