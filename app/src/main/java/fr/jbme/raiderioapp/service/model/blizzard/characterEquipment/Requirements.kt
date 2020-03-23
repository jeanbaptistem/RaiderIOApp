package fr.jbme.raiderioapp.service.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName


data class Requirements(

    @SerializedName("level") val level: Level
)