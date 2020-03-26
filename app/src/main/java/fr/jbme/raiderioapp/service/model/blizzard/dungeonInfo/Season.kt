package fr.jbme.raiderioapp.service.model.blizzard.dungeonInfo

import com.google.gson.annotations.SerializedName


data class Season(

    @SerializedName("key") val key: Key,
    @SerializedName("id") val id: Int
)