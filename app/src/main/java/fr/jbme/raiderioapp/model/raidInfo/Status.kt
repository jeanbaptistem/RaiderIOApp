package fr.jbme.raiderioapp.model.raidInfo

import com.google.gson.annotations.SerializedName

data class Status(

    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String
)