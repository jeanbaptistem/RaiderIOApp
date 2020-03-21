package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Damage(

    @SerializedName("damage_class") val damage_class: Damage_class,
    @SerializedName("display_string") val display_string: String,
    @SerializedName("max_value") val max_value: Int,
    @SerializedName("min_value") val min_value: Int
)