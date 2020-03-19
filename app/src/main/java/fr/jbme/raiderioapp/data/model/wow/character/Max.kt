package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Max(

    @SerializedName("type") val type: String,
    @SerializedName("value") val value: Double
)