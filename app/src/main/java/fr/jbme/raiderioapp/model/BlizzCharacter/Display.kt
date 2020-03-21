package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Display(

    @SerializedName("color") val color: Color,
    @SerializedName("display_string") val display_string: String
)