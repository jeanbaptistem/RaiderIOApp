package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class PvpTalents(

    @SerializedName("cast") val cast: String,
    @SerializedName("cooldown") val cooldown: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: Icon,
    @SerializedName("level") val level: Int,
    @SerializedName("name") val name: String,
    @SerializedName("slot") val slot: Int
)