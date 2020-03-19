package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Media(

    @SerializedName("content") val content: Content,
    @SerializedName("id") val id: Int,
    @SerializedName("key") val key: Key
)