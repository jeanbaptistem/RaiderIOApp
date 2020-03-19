package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Pve(

    @SerializedName("mythic") val mythic: String,
    @SerializedName("raids") val raids: List<Raids>
)