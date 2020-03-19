package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Content(

    @SerializedName("assets") val assets: List<Assets>,
    @SerializedName("dataRegion") val dataRegion: DataRegion
)