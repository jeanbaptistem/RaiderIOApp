package fr.jbme.raiderioapp.service.network.service

import fr.jbme.raiderioapp.service.model.raiderio.characterScore.CharacterScore
import fr.jbme.raiderioapp.service.model.raiderio.dungeonRanks.DungeonsRanks
import fr.jbme.raiderioapp.service.model.raiderio.dungeonsBestRuns.BestRuns
import fr.jbme.raiderioapp.service.model.raiderio.raidInfoRio.RaidInfoRio
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RaiderIOService {

    @Headers("No-Authentication: true")
    @GET("characters/profile?fields=raid_progression")
    fun getRaidCharacter(
        @Query("region") region: String?,
        @Query("realm") realmName: String?,
        @Query("name") characterName: String?
    ): Call<RaidInfoRio>

    @Headers("No-Authentication: true")
    @GET("characters/profile?fields=mythic_plus_ranks")
    fun getCharacterRanks(
        @Query("region") region: String?,
        @Query("realm") realmName: String?,
        @Query("name") characterName: String?
    ): Call<DungeonsRanks>

    @Headers("No-Authentication: true")
    @GET("characters/profile?fields=mythic_plus_best_runs%3Aall")
    fun getCharacterBestRuns(
        @Query("region") region: String,
        @Query("realm") realmName: String,
        @Query("name") characterName: String
    ): Call<BestRuns>


    @Headers("No-Authentication: true")
    @GET("characters/profile?fields=mythic_plus_scores_by_season%3Acurrent")
    fun getCharacterScore(
        @Query("region") region: String,
        @Query("realm") realmName: String,
        @Query("name") characterName: String
    ): Call<CharacterScore>

}