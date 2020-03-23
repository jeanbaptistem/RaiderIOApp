package fr.jbme.raiderioapp.service.repository

import fr.jbme.raiderioapp.service.model.raiderio.RaidInfoRio
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
}