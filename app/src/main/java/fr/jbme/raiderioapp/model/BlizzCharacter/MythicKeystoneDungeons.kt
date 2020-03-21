package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName

data class MythicKeystoneDungeons(

    @SerializedName("season") val season: Int,
    @SerializedName("expansion") val expansion: String,
    @SerializedName("dungeons") val dungeons: List<Dungeons>
)