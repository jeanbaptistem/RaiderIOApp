package fr.jbme.raiderioapp.data.model.character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName("head")
    @Expose
    var head: GearItem? = null,

    @SerializedName("neck")
    @Expose
    var neck: GearItem? = null,

    @SerializedName("shoulder")
    @Expose
    var shoulder: GearItem? = null,

    @SerializedName("back")
    @Expose
    var back: GearItem? = null,

    @SerializedName("chest")
    @Expose
    var chest: GearItem? = null,

    @SerializedName("waist")
    @Expose
    var waist: GearItem? = null,

    @SerializedName("wrist")
    @Expose
    var wrist: GearItem? = null,

    @SerializedName("hands")
    @Expose
    var hands: GearItem? = null,

    @SerializedName("legs")
    @Expose
    var legs: GearItem? = null,

    @SerializedName("feet")
    @Expose
    var feet: GearItem? = null,

    @SerializedName("finger1")
    @Expose
    var finger1: GearItem? = null,

    @SerializedName("finger2")
    @Expose
    var finger2: GearItem? = null,

    @SerializedName("trinket1")
    @Expose
    var trinket1: GearItem? = null,

    @SerializedName("trinket2")
    @Expose
    var trinket2: GearItem? = null,

    @SerializedName("mainhand")
    @Expose
    var mainhand: GearItem? = null,

    @SerializedName("offhand")
    @Expose
    var offhand: GearItem? = null
)