package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class CharacterDungeon(

    @SerializedName("description") val description: String,
    @SerializedName("expansion") val expansion: Expansion,
    @SerializedName("icon") val icon: Icon,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("thumbnail") val thumbnail: Thumbnail,
    @SerializedName("url") val url: String
)