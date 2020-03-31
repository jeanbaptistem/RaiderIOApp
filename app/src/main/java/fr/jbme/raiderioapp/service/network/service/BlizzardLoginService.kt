package fr.jbme.raiderioapp.service.network.service

import fr.jbme.raiderioapp.BuildConfig
import fr.jbme.raiderioapp.service.model.login.AccessTokenResponse
import fr.jbme.raiderioapp.service.model.login.TokenCheckResponse
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface BlizzardLoginService {

    @Headers("No-Authentication: true")
    @POST("oauth/token?redirect_uri=${BuildConfig.REDIRECTED_URL}&scope=wow.profile&grant_type=authorization_code")
    fun requestAccessToken(
        @Header("Authorization") credentials: String,
        @Query("code") code: String
    ): Call<AccessTokenResponse>

    @Headers("No-Authentication: true")
    @POST("/oauth/check_token")
    fun checkTokenValidity(@Query("token") token: String): Call<TokenCheckResponse>
}