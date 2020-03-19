package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Sockets(

    @SerializedName("display_string") val display_string: String,
    @SerializedName("item") val item: Item,
    @SerializedName("media") val media: Media,
    @SerializedName("socket_type") val socket_type: SocketType
)