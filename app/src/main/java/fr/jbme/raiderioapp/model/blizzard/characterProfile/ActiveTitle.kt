package fr.jbme.raiderioapp.model.blizzard.characterProfile

import com.google.gson.annotations.SerializedName


data class ActiveTitle(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("display_string") val display_string: String
)