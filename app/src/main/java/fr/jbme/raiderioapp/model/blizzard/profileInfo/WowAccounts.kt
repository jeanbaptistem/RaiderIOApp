package fr.jbme.raiderioapp.model.blizzard.profileInfo

import com.google.gson.annotations.SerializedName

data class WowAccounts(

    @SerializedName("id") val id: Int,
    @SerializedName("characters") val characters: List<Characters>
)