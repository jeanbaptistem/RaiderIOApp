package fr.jbme.raiderioapp.data.model.character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Corruption(
    @SerializedName("added")
    @Expose
    var added: Int? = null,

    @SerializedName("resisted")
    @Expose
    var resisted: Int? = null,

    @SerializedName("total")
    @Expose
    var total: Int? = null,

    @SerializedName("cloakRank")
    @Expose
    var cloakRank: Int? = null,

    @SerializedName("spells")
    @Expose
    var spells: List<Spell>? = null
)