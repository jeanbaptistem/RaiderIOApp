package fr.jbme.raiderioapp.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Damage(
    @SerializedName("min_value")
    @Expose
    var minValue: Int? = null,

    @SerializedName("max_value")
    @Expose
    var maxValue: Int? = null,

    @SerializedName("display_string")
    @Expose
    var displayString: String? = null,

    @SerializedName("damage_class")
    @Expose
    var damageClass: DamageClass? = null
)