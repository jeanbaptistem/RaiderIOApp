package fr.jbme.raiderioapp.service.model.blizzard.raidInfo

import com.google.gson.annotations.SerializedName

data class Instances(

    @SerializedName("instance") val instance: Instance,
    @SerializedName("modes") val modes: List<Modes>
)