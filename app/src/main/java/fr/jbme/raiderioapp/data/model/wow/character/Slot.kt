package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Slot(

    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String
)