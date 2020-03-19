package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Primary(

    @SerializedName("enum") val enum: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("value") val value: Value
)