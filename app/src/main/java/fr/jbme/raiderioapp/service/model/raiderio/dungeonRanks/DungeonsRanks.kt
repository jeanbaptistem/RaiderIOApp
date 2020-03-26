package fr.jbme.raiderioapp.service.model.raiderio.dungeonRanks

data class DungeonsRanks(

    val name: String?,
    val race: String?,
    val _class: String?,
    val active_spec_name: String?,
    val active_spec_role: String?,
    val gender: String?,
    val faction: String?,
    val achievement_points: Int?,
    val honorable_kills: Int?,
    val thumbnail_url: String?,
    val region: String?,
    val realm: String?,
    val profile_url: String?,
    val profile_banner: String?,
    val mythic_plus_ranks: List<Rank>?
)