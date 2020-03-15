package fr.jbme.raiderioapp.data.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Stat(
    @SerializedName("type")
    @Expose
    var type: Type? = null,

    @SerializedName("value")
    @Expose
    var value: Int? = null,

    @SerializedName("display")
    @Expose
    var display: Display? = null,

    @SerializedName("is_equip_bonus")
    @Expose
    var isEquipBonus: Boolean? = null
)