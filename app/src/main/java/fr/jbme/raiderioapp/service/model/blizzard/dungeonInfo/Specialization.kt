package fr.jbme.raiderioapp.service.model.blizzard.dungeonInfo

import com.google.gson.annotations.SerializedName


data class Specialization(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int
)