package fr.jbme.raiderioapp.service.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName

data class Effects(

    @SerializedName("display_string") val display_string: String,
    @SerializedName("required_count") val required_count: Int
)