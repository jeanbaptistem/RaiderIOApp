package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class AzeriteDetails(

    @SerializedName("selected_powers") val selected_powers: List<SelectedPowers>,
    @SerializedName("selected_powers_string") val selected_powers_string: String
)