package fr.jbme.raiderioapp.service.model.blizzard.profileInfo

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("href") val href: String
)