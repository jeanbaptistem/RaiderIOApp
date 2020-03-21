package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Content(

    @SerializedName("assets") val assets: List<Assets>,
    @SerializedName("dataRegion") val dataRegion: DataRegion
)