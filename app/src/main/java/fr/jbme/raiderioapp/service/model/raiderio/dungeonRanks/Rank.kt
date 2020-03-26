package fr.jbme.raiderioapp.service.model.raiderio.dungeonRanks

import java.io.Serializable

data class Rank(

    val name: String?,
    val world: Int?,
    val region: Int?,
    val realm: Int?
) : Serializable