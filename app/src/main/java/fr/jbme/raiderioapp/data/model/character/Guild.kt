package fr.jbme.raiderioapp.data.model.character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Guild(
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("realm")
    @Expose
    var realm: String? = null
)