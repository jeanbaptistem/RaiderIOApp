package fr.jbme.raiderioapp.network.services

import fr.jbme.raiderioapp.data.model.itemInfo.ItemInfoResponse
import fr.jbme.raiderioapp.data.model.itemInfo.ItemMediaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BlizzardService {

    @GET("data/wow/media/item/{itemId}?namespace=static-eu&locale=en_EN")
    fun getItemMediaInfo(
        @Path("itemId") itemId: Int,
        @Query("access_token") token: String
    ): Call<ItemMediaResponse>

    @GET("https://eu.api.blizzard.com/data/wow/item/{itemId}?namespace=static-eu&locale=fr_FR")
    fun getItemInfo(
        @Path("itemId") itemId: Int,
        @Query("access_token") token: String
    ): Call<ItemInfoResponse>

}