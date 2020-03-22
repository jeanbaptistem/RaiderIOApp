package fr.jbme.raiderioapp.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName


data class Requirements(

    @SerializedName("level") val level: Level
)