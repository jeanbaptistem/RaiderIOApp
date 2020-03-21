package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName

data class GearStats(
    @SerializedName("display") var display: Display,
    @SerializedName("type") var type: Type,
    @SerializedName("value") var value: Int
)