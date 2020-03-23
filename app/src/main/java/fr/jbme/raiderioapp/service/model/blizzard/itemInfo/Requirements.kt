package fr.jbme.raiderioapp.service.model.blizzard.itemInfo

import com.google.gson.annotations.SerializedName


data class Requirements(

    @SerializedName("level") val level: Level
)