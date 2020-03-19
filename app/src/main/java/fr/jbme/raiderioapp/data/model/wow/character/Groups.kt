package fr.jbme.raiderioapp.data.model.wow.character

import com.google.gson.annotations.SerializedName


data class Groups(

    @SerializedName("name") val name: String,
    @SerializedName("stats") val stats: List<Stats>
)