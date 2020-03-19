package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Pvp(

    @SerializedName("honorableKills") val honorableKills: HonorableKills,
    @SerializedName("prestige") val prestige: Prestige,
    @SerializedName("ratings") val ratings: Ratings,
    @SerializedName("region") val region: String
)