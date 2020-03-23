package fr.jbme.raiderioapp.service.model.blizzard.profileInfo

import com.google.gson.annotations.SerializedName


data class _links(

    @SerializedName("self") val self: Self,
    @SerializedName("user") val user: User,
    @SerializedName("profile") val profile: Profile
)