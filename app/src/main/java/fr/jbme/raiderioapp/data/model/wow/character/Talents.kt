package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Talents(

    @SerializedName("cast") val cast: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: Icon,
    @SerializedName("index") val index: Int,
    @SerializedName("level") val level: Int,
    @SerializedName("name") val name: String
)