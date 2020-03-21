package fr.jbme.raiderioapp.model.RIOCharacter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HeartOfAzeroth(
    @SerializedName("essences")
    @Expose
    var essences: List<Essence>? = null,

    @SerializedName("level")
    @Expose
    var level: Int? = null,

    @SerializedName("progress")
    @Expose
    var progress: Float? = null
)