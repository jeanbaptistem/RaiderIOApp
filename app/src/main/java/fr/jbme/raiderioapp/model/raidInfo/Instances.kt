package fr.jbme.raiderioapp.model.raidInfo

import com.google.gson.annotations.SerializedName

data class Instances(

    @SerializedName("instance") val instance: Instance,
    @SerializedName("modes") val modes: List<Modes>
)