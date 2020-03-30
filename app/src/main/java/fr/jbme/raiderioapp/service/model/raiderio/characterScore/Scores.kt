package fr.jbme.raiderioapp.service.model.raiderio.characterScore

import com.google.gson.annotations.SerializedName

data class Scores(

    @SerializedName("all") val all: Double,
    @SerializedName("dps") val dps: Double,
    @SerializedName("healer") val healer: Int,
    @SerializedName("tank") val tank: Int,
    @SerializedName("spec_0") val spec_0: Double,
    @SerializedName("spec_1") val spec_1: Double,
    @SerializedName("spec_2") val spec_2: Int,
    @SerializedName("spec_3") val spec_3: Int
)