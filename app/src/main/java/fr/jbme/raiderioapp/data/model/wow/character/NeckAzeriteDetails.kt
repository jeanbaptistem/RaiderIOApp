package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName

data class NeckAzeriteDetails(
    @SerializedName("percentage_to_next_level") var percentage_to_next_level: Double,
    @SerializedName("selected_essences") var selected_essences: List<SelectedEssences>,
    @SerializedName("power_level") var power_level: PowerLevel
)