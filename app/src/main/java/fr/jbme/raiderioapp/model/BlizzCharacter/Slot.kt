package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Slot(

    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String
)