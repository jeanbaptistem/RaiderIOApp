package fr.jbme.raiderioapp.service.model.raiderio.characterScore

import com.google.gson.annotations.SerializedName

data class MythicPlusScoresBySeason(

    @SerializedName("season") val season: String,
    @SerializedName("scores") val scores: Scores
)