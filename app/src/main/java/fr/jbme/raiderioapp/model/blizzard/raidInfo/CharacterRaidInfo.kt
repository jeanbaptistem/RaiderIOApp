package fr.jbme.raiderioapp.model.blizzard.raidInfo

import com.google.gson.annotations.SerializedName

data class CharacterRaidInfo(

    @SerializedName("fr.jbme.raiderioapp.model.blizzard.itemInfo.fr.jbme.raiderioapp.model.blizzard.itemMedia._links") val Links: Links,
    @SerializedName("character") val character: Character,
    @SerializedName("expansions") val expansions: List<Expansions>
)