package fr.jbme.raiderioapp.service.network.service

import fr.jbme.raiderioapp.service.model.raiderio.dungeonRanks.DungeonsRanks
import fr.jbme.raiderioapp.service.model.raiderio.raidInfoRio.RaidInfoRio
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RaiderIOService {

    @GET("characters/profile?fields=raid_progression")
    fun getRaidCharacter(
        @Query("region") region: String?,
        @Query("realm") realmName: String?,
        @Query("name") characterName: String?
    ): Call<RaidInfoRio>

    @GET("characters/profile?fields=mythic_plus_ranks")
    fun getCharacterRanks(
        @Query("region") region: String?,
        @Query("realm") realmName: String?,
        @Query("name") characterName: String?
    ): Call<DungeonsRanks>

}