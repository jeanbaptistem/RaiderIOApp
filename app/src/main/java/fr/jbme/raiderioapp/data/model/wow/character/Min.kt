package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Min(

    @SerializedName("type") val type: String,
    @SerializedName("value") val value: Double
)