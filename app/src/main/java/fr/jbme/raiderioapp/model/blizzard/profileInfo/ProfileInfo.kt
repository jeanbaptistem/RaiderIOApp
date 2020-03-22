package fr.jbme.raiderioapp.model.blizzard.profileInfo

import com.google.gson.annotations.SerializedName


data class ProfileInfo(

    @SerializedName("fr.jbme.raiderioapp.model.blizzard.itemInfo.fr.jbme.raiderioapp.model.blizzard.itemMedia._links") val _links: _links,
    @SerializedName("id") val id: Int,
    @SerializedName("wow_accounts") val wow_accounts: List<WowAccounts>,
    @SerializedName("collections") val collections: Collections
)