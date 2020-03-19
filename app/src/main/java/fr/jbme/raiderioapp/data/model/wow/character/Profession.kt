package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Profession(

    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)