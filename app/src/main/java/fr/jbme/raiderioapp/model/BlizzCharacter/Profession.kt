package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Profession(

    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)