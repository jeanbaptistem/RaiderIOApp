package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Overview(

    @SerializedName("enum") val enum: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("value") val value: Value
)