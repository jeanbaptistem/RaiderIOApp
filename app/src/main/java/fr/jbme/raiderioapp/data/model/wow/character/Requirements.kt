package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Requirements(

    @SerializedName("level") val level: Level,
    @SerializedName("skill") val skill: Skill
)