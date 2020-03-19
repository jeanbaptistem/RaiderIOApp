package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Raids(

    @SerializedName("description") val description: String,
    @SerializedName("difficulties") val difficulties: List<Difficulties>,
    @SerializedName("icon") val icon: Icon,
    @SerializedName("id") val id: String,
    @SerializedName("level") val level: Int,
    @SerializedName("location") val location: String,
    @SerializedName("name") val name: String,
    @SerializedName("players") val players: String
)