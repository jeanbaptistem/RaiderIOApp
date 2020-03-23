package fr.jbme.raiderioapp.service.model.blizzard.itemInfo

import com.google.gson.annotations.SerializedName


data class Weapon(

    @SerializedName("damage") val damage: Damage,
    @SerializedName("attack_speed") val attack_speed: AttackSpeed,
    @SerializedName("dps") val dps: Dps
)