package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Skill(

    @SerializedName("display_string") val display_string: String,
    @SerializedName("level") val level: Int,
    @SerializedName("profession") val profession: Profession
)