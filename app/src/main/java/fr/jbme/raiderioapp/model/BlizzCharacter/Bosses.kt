package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Bosses(

    @SerializedName("killCount") val killCount: Int,
    @SerializedName("lastTimestamp") val lastTimestamp: Long,
    @SerializedName("name") val name: String
)