package fr.jbme.raiderioapp.service.model.blizzard.itemInfo

import com.google.gson.annotations.SerializedName


data class ItemSubclass(

    @SerializedName("key") val key: Key,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int
)