package fr.jbme.raiderioapp.network.services

import fr.jbme.raiderioapp.model.RIOCharacter.RIOCharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RaiderIOService {

    @GET("characters/profile")
    fun getCharacterInfo(
        @Query("region") region: String,
        @Query("realm") realmName: String,
        @Query("name") characterName: String
    ): Call<RIOCharacterResponse>

    @GET("characters/profile?fields=gear")
    fun getGearedCharacter(
        @Query("region") region: String,
        @Query("realm") realmName: String,
        @Query("name") characterName: String
    ): Call<RIOCharacterResponse>

    @GET("characters/profile?fields=raid_progression")
    fun getRaidCharacter(
        @Query("region") region: String,
        @Query("realm") realmName: String,
        @Query("name") characterName: String
    ): Call<RIOCharacterResponse>
}