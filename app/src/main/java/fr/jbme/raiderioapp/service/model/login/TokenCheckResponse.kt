package fr.jbme.raiderioapp.service.model.login

import com.google.gson.annotations.SerializedName

data class TokenCheckResponse(

    @SerializedName("exp") val exp: Int,
    @SerializedName("user_name") val userName: Int,
    @SerializedName("authorities") val authorities: List<String>,
    @SerializedName("client_id") val clientId: String,
    @SerializedName("scope") val scope: List<String>
)