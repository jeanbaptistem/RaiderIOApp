package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class SourceItem(

    @SerializedName("id") val id: Int,
    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String
)