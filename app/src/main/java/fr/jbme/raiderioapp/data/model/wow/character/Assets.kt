package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Assets(

    @SerializedName("key") val key: String,
    @SerializedName("value") val value: String
)