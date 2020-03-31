package fr.jbme.raiderioapp.service.model.blizzard


import com.google.gson.annotations.SerializedName

data class ItemMedia(
    @SerializedName("assets")
    val assets: List<Asset?>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("_links")
    val links: Links?
) {
    data class Asset(
        @SerializedName("key")
        val key: String?,
        @SerializedName("value")
        val value: String?
    )

    data class Links(
        @SerializedName("self")
        val self: Self?
    ) {
        data class Self(
            @SerializedName("href")
            val href: String?
        )
    }
}