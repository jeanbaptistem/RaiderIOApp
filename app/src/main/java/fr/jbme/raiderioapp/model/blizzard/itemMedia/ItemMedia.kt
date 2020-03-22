package fr.jbme.raiderioapp.model.blizzard.itemMedia

import com.google.gson.annotations.SerializedName
import fr.jbme.raiderioapp.model.blizzard.itemInfo._links

data class ItemMedia(

    @SerializedName("_links") val _links: _links,
    @SerializedName("assets") val assets: List<Assets>,
    @SerializedName("id") val id: Int
)