package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Guild(

    @SerializedName("faction") val faction: Faction,
    @SerializedName("name") val name: String,
    @SerializedName("realm") val realm: Realm,
    @SerializedName("region") val region: String,
    @SerializedName("url") val url: String
)