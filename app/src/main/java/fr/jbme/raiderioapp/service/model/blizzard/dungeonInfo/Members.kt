package fr.jbme.raiderioapp.service.model.blizzard.dungeonInfo

import com.google.gson.annotations.SerializedName


data class Members(

    @SerializedName("character") val character: Character,
    @SerializedName("specialization") val specialization: Specialization,
    @SerializedName("race") val race: Race,
    @SerializedName("equipped_item_level") val equipped_item_level: Int
)