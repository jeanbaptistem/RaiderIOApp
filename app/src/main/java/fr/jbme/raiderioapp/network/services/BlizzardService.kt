package fr.jbme.raiderioapp.network.services

import fr.jbme.raiderioapp.model.itemInfo.BlizMediaResponse
import fr.jbme.raiderioapp.model.itemInfo.ItemInfoResponse
import fr.jbme.raiderioapp.model.raidInfo.RaidInfoResponse
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

    @GET("/profile/wow/character/{realmSlug}/{characterName}/encounters/raids?namespace=profile-eu&locale=en_GB")
    fun getRaidInfo(
        @Path("realmSlug") realmSlug: String,
        @Path("characterName") characterName: String,
        @Query("access_token") token: String
    ): Call<RaidInfoResponse>
}