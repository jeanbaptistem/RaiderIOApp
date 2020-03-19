package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class BlizCharacterResponse(

    @SerializedName("character") val character: Character,
    @SerializedName("region") val region: String,
    @SerializedName("characterMythicKeystoneDungeons") val mythicKeystoneDungeons: MythicKeystoneDungeons,
    @SerializedName("urls") val urls: Urls
)