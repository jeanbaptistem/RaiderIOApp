package fr.jbme.raiderioapp.data.model.character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AzeritePower(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("spell")
    @Expose
    var spell: Spell? = null,

    @SerializedName("tier")
    @Expose
    var tier: Int? = null
)