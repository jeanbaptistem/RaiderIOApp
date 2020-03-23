package fr.jbme.raiderioapp.service.model.blizzard.characterProfile

import com.google.gson.annotations.SerializedName


data class Guild(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("realm") val realm: Realm,
    @SerializedName("faction") val faction: Faction
)