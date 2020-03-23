package fr.jbme.raiderioapp.service.model.blizzard.raidInfo

import com.google.gson.annotations.SerializedName

data class Progress(

    @SerializedName("completed_count") val completed_count: Int,
    @SerializedName("total_count") val total_count: Int,
    @SerializedName("encounters") val encounters: List<Encounters>
)