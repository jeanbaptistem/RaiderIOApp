package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class NameDescription(

    @SerializedName("color") val color: Color,
    @SerializedName("display_string") val display_string: String
)