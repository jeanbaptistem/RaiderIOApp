package fr.jbme.raiderioapp.service.model.blizzard.itemMedia

import com.google.gson.annotations.SerializedName

data class Assets(

    @SerializedName("key") val key: String,
    @SerializedName("value") val value: String
)