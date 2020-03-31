package fr.jbme.raiderioapp.service.model.login

import com.google.gson.annotations.SerializedName

data class AccessTokenResponse(

    @SerializedName("access_token") val accessToken: String,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("expires_in") val expiresIn: Int,
    @SerializedName("scope") val scope: String
)