package fr.jbme.raiderioapp.model.blizzard.characterMedia

import com.google.gson.annotations.SerializedName

data class CharacterMedia(

    @SerializedName("fr.jbme.raiderioapp.model.blizzard.itemInfo.fr.jbme.raiderioapp.model.blizzard.itemMedia._links") val _links: _links,
    @SerializedName("character") val character: Character,
    @SerializedName("avatar_url") val avatar_url: String,
    @SerializedName("bust_url") val bust_url: String,
    @SerializedName("render_url") val render_url: String
)