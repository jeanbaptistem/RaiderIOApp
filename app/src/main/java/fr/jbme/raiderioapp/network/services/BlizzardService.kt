package fr.jbme.raiderioapp.network.services

import fr.jbme.raiderioapp.data.model.itemInfo.ItemInfoResponse
import fr.jbme.raiderioapp.data.model.itemInfo.ItemMediaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BlizzardService {

    @GET("data/wow/media/item/{itemId}?namespace=static-eu&locale=en_EN&access_token=USF68d1iQpj28NtcidiM3gYwK8zdIT2CHR")
    fun getItemMediaInfo(@Path("itemId") itemId: Int): Call<ItemMediaResponse>

    @GET("https://eu.api.blizzard.com/data/wow/item/{itemId}?namespace=static-eu&locale=fr_FR&access_token=USF68d1iQpj28NtcidiM3gYwK8zdIT2CHR")
    fun getItemInfo(@Path("itemId") itemId: Int): Call<ItemInfoResponse>

}