package fr.jbme.raiderioapp.service.model.blizzard.login

import com.google.gson.annotations.SerializedName

data class AccessTokenResponse(

    @SerializedName("access_token") val access_token: String,
    @SerializedName("token_type") val token_type: String,
    @SerializedName("expires_in") val expires_in: Int,
    @SerializedName("scope") val scope: String
)