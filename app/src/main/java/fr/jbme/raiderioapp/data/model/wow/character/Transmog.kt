package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Transmog(

    @SerializedName("display_string") val display_string: String,
    @SerializedName("item") val item: Item
)