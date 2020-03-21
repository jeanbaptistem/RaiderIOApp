package fr.jbme.raiderioapp.model.BlizzCharacter

import com.google.gson.annotations.SerializedName


data class Difficulties(

    @SerializedName("bosses") val bosses: List<Bosses>,
    @SerializedName("count") val count: Int,
    @SerializedName("difficulty") val difficulty: Difficulty,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("progress") val progress: Progress,
    @SerializedName("total") val total: Int
)