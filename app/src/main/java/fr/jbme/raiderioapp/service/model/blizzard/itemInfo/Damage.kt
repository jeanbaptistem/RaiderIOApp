package fr.jbme.raiderioapp.service.model.blizzard.itemInfo

import com.google.gson.annotations.SerializedName

data class Damage(

    @SerializedName("min_value") val min_value: Int,
    @SerializedName("max_value") val max_value: Int,
    @SerializedName("display_string") val display_string: String,
    @SerializedName("damage_class") val damage_class: DamageClass
)