package fr.jbme.raiderioapp.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BlizMediaResponse(

    @SerializedName("Links")
    @Expose
    val Links: Links,
    @SerializedName("assets")
    @Expose
    val assets: List<Assets>,
    @SerializedName("id")
    @Expose
    val id: Int
)