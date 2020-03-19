package fr.jbme.raiderioapp.network.services

import fr.jbme.raiderioapp.data.model.wow.character.BlizCharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BlizzardWebService {

    @GET("{region}/{realm}/{name}/model.json")
    fun getCharacterInfo(
        @Path("region") region: String,
        @Path("realm") realm: String,
        @Path("name") name: String
    ): Call<BlizCharacterResponse>
}