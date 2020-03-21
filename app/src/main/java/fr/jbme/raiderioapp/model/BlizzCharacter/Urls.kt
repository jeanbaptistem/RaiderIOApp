package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Urls(

    @SerializedName("pve") val pve: PveUrl,
    @SerializedName("pvp") val pvp: String,
    @SerializedName("stable") val stable: String,
    @SerializedName("achievements") val achievements: String
)