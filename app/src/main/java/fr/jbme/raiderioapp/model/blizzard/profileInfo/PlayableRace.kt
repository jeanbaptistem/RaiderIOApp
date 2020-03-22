package fr.jbme.raiderioapp.model.blizzard.profileInfo

import com.google.gson.annotations.SerializedName

data class PlayableRace(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int
)