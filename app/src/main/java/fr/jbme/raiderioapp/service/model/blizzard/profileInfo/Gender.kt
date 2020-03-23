package fr.jbme.raiderioapp.service.model.blizzard.profileInfo

import com.google.gson.annotations.SerializedName

data class Gender(

    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String
)