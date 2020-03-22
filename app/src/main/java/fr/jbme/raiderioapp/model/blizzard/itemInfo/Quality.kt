package fr.jbme.raiderioapp.model.blizzard.itemInfo

import com.google.gson.annotations.SerializedName

data class Quality(

    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String
)