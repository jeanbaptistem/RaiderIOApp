package fr.jbme.raiderioapp.service.model.blizzard.itemInfo

import com.google.gson.annotations.SerializedName


data class Media(

    @SerializedName("key") val key: Key,
    @SerializedName("id") val id: Int
)