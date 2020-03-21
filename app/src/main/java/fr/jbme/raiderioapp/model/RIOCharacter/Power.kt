package fr.jbme.raiderioapp.model.RIOCharacter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Power(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("essence")
    @Expose
    var essence: Essence? = null,

    @SerializedName("tierId")
    @Expose
    var tierId: Int? = null,

    @SerializedName("majorPowerSpell")
    @Expose
    var majorPowerSpell: Spell? = null,

    @SerializedName("minorPowerSpell")
    @Expose
    var minorPowerSpell: Spell? = null
)