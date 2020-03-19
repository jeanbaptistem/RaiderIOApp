package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class EnchantmentSlot(

    @SerializedName("id") val id: Int,
    @SerializedName("type") val type: String
)