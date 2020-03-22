package fr.jbme.raiderioapp.model.blizzard.characterEquipment

import com.google.gson.annotations.SerializedName

data class AzeriteDetails(

    @SerializedName("selected_powers") val selected_powers: List<SelectedPowers>?,
    @SerializedName("selected_powers_string") val selected_powers_string: String?,

    @SerializedName("percentage_to_next_level") val percentage_to_next_level: Double?,
    @SerializedName("selected_essences") val selected_essences: List<SelectedEssences>?,
    @SerializedName("level") val level: Level?

)