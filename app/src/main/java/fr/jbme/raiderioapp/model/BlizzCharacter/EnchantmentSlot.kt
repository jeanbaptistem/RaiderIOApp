package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class EnchantmentSlot(

    @SerializedName("id") val id: Int,
    @SerializedName("type") val type: String
)