package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Item(

    @SerializedName("id") val id: Int,
    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String
)