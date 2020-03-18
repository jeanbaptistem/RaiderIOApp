package fr.jbme.raiderioapp.data.model.raidInfo

import com.google.gson.annotations.SerializedName

data class Modes(

    @SerializedName("difficulty") val difficulty: Difficulty,
    @SerializedName("status") val status: Status,
    @SerializedName("progress") val progress: Progress
)