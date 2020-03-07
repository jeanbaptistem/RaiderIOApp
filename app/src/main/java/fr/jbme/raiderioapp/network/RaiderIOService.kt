package fr.jbme.raiderioapp.network

import fr.jbme.raiderioapp.data.model.character.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RaiderIOService {

    @GET("characters/profile")
    fun getCharacterInfo(
        @Query("region") region: String,
        @Query("realm") realmName: String,
        @Query("name") characterName: String
    ): Call<CharacterResponse>
}