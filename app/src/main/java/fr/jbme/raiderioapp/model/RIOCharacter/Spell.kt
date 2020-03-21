package fr.jbme.raiderioapp.model.RIOCharacter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Spell(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("school")
    @Expose
    var school: Int? = null,

    @SerializedName("icon")
    @Expose
    var icon: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("rank")
    @Expose
    var rank: String? = null
)