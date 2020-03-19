package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName

data class WeaponStats(
    @SerializedName("attack_speed") var attack_speed: AttackSpeed,
    @SerializedName("damage") var damage: Damage,
    @SerializedName("dps") var dps: Dps

)