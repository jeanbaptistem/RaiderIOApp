package fr.jbme.raiderioapp.service.network.service

import fr.jbme.raiderioapp.REDIRECTED_URI
import fr.jbme.raiderioapp.service.model.blizzard.login.AccessTokenResponse
import fr.jbme.raiderioapp.service.model.blizzard.login.TokenCheckResponse
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface BlizzardLoginService {

    @POST("oauth/token?redirect_uri=${REDIRECTED_URI}&scope=wow.profile&grant_type=authorization_code")
    fun requestAccessToken(
        @Header("Authorization") credentials: String,
        @Query("code") code: String
    ): Call<AccessTokenResponse>

    @POST("/oauth/check_token")
    fun checkTokenValidity(@Query("token") token: String): Call<TokenCheckResponse>
}