package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Transmog(

    @SerializedName("display_string") val display_string: String,
    @SerializedName("item") val item: Item
)