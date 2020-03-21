package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class CharacterStats(

    @SerializedName("basic") val basic: Basic,
    @SerializedName("groups") val groups: List<Groups>,
    @SerializedName("overview") val overview: List<Overview>
)