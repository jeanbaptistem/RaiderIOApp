package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class BossEncounter(

    @SerializedName("description") var description: String,
    @SerializedName("id") var id: Int,
    @SerializedName("location") var location: String,
    @SerializedName("name") var name: String,
    @SerializedName("urlSlug") var urlSlug: String
)