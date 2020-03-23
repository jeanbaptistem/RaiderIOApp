package fr.jbme.raiderioapp.service.model.blizzard.itemMedia

import com.google.gson.annotations.SerializedName

data class Media(

    @SerializedName("_links") val _links: _links,
    @SerializedName("assets") val assets: List<Assets>,
    @SerializedName("id") val id: Int
)