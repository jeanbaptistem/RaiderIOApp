package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Name_description(

    @SerializedName("color") val color: Color,
    @SerializedName("display_string") val display_string: String
)