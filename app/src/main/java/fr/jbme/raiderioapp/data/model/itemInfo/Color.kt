package fr.jbme.raiderioapp.data.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Color(
    @SerializedName("r")
    @Expose
    var r: Int? = null,

    @SerializedName("g")
    @Expose
    var g: Int? = null,

    @SerializedName("b")
    @Expose
    var b: Int? = null,

    @SerializedName("a")
    @Expose
    var a: Int? = null
)