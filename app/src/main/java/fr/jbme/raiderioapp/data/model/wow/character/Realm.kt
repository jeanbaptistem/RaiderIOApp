package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Realm(

    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String
)