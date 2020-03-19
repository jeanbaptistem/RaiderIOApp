package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Modifiers(

    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: Icon,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)