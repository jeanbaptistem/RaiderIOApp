package fr.jbme.raiderioapp.service.model.blizzard.login

import com.google.gson.annotations.SerializedName

data class TokenCheckResponse(

    @SerializedName("exp") val exp: Int,
    @SerializedName("user_name") val user_name: Int,
    @SerializedName("authorities") val authorities: List<String>,
    @SerializedName("client_id") val client_id: String,
    @SerializedName("scope") val scope: List<String>
)