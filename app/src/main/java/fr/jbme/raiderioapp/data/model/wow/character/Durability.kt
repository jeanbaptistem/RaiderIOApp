package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Durability(

    @SerializedName("display_string") val display_string: String,
    @SerializedName("value") val value: Int
)