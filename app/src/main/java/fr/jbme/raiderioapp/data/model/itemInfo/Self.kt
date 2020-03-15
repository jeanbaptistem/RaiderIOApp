package fr.jbme.raiderioapp.data.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Self(
    @SerializedName("href")
    @Expose
    var href: String? = null
)