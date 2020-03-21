package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Groups(

    @SerializedName("name") val name: String,
    @SerializedName("stats") val stats: List<Stats>
)