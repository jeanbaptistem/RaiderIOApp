package fr.jbme.raiderioapp.service.network.service

import fr.jbme.raiderioapp.service.model.wowprogress.WoWProgressResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WoWProgressService {

    @Headers("No-Authentication: true", "Accept: text/html")
    @GET("search/autocomplete?type=char&infix=1")
    fun searchAutoComplete(@Query("q") query: String): Call<WoWProgressResponse>
}
