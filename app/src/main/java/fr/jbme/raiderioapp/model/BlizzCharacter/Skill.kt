package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Skill(

    @SerializedName("display_string") val display_string: String,
    @SerializedName("level") val level: Int,
    @SerializedName("profession") val profession: Profession
)