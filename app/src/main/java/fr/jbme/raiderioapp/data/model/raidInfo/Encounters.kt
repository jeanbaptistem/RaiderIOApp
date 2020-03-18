package fr.jbme.raiderioapp.data.model.raidInfo

import com.google.gson.annotations.SerializedName

data class Encounters(

    @SerializedName("encounter") val encounter: Encounter,
    @SerializedName("completed_count") val completed_count: Int,
    @SerializedName("last_kill_timestamp") val last_kill_timestamp: Long
)