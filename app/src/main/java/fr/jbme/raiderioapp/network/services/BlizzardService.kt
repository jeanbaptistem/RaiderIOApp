package fr.jbme.raiderioapp.network.services

import fr.jbme.raiderioapp.data.model.itemInfo.BlizMediaResponse
import fr.jbme.raiderioapp.data.model.itemInfo.ItemInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BlizzardService {

    @GET("data/wow/media/item/{itemId}?namespace=static-eu&locale=en_GB")
    fun getItemMediaInfo(
        @Path("itemId") itemId: Int,
        @Query("access_token") token: String
    ): Call<BlizMediaResponse>

    @GET("data/wow/item/{itemId}?namespace=static-eu&locale=en_GB")
    fun getItemInfo(
        @Path("itemId") itemId: Int,
        @Query("access_token") token: String
    ): Call<ItemInfoResponse>
}