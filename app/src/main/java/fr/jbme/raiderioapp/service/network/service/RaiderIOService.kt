package fr.jbme.raiderioapp.service.network.service

import fr.jbme.raiderioapp.service.model.raiderio.CharacterBestRuns
import fr.jbme.raiderioapp.service.model.raiderio.CharacterRaid
import fr.jbme.raiderioapp.service.model.raiderio.CharacterRanks
import fr.jbme.raiderioapp.service.model.raiderio.CharacterScore
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RaiderIOService {

    @Headers("No-Authentication: true")
    @GET("characters/profile?fields=raid_progression")
    fun getRaidCharacterRio(
        @Query("region") region: String?,
        @Query("realm") realmName: String?,
        @Query("name") characterName: String?
    ): Call<CharacterRaid>

    @Headers("No-Authentication: true")
    @GET("characters/profile?fields=mythic_plus_ranks")
    fun getCharacterRanksRio(
        @Query("region") region: String?,
        @Query("realm") realmName: String?,
        @Query("name") characterName: String?
    ): Call<CharacterRanks>

    @Headers("No-Authentication: true")
    @GET("characters/profile?fields=mythic_plus_best_runs%3Aall")
    fun getCharacterBestRunsRio(
        @Query("region") region: String,
        @Query("realm") realmName: String,
        @Query("name") characterName: String
    ): Call<CharacterBestRuns>


    @Headers("No-Authentication: true")
    @GET("characters/profile?fields=mythic_plus_scores_by_season%3Acurrent")
    fun getCharacterScoreRio(
        @Query("region") region: String,
        @Query("realm") realmName: String,
        @Query("name") characterName: String
    ): Call<CharacterScore>

}