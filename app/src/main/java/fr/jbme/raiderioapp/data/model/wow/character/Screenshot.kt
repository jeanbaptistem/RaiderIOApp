package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Screenshot(
    @SerializedName("url") var url: String
)