package fr.jbme.raiderioapp.model.RIOCharacter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Affix(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("wowhead_url")
    @Expose
    var wowheadUrl: String? = null
)