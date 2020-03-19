package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class HonorableKills(

    @SerializedName("tier") val tier: Int,
    @SerializedName("value") val value: Int
)