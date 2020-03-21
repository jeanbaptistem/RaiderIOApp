package fr.jbme.raiderioapp.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Assets(

    @SerializedName("key")
    @Expose
    val key: String,

    @SerializedName("value")
    @Expose
    val value: String
)