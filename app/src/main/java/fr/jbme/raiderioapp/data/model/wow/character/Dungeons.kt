package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName

data class Dungeons(

    @SerializedName("characterDungeon") val dungeon: Dungeon
)