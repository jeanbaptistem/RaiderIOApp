package fr.jbme.raiderioapp.data.model.itemInfo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Weapon(
    @SerializedName("damage")
    @Expose
    var damage: Damage? = null,

    @SerializedName("attack_speed")
    @Expose
    var attackSpeed: AttackSpeed? = null,

    @SerializedName("dps")
    @Expose
    var dps: Dps? = null
)