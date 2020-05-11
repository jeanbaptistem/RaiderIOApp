package fr.jbme.raiderioapp.service.model.blizzard


import com.google.gson.annotations.SerializedName

data class CharacterMedia(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("bust_url")
    val bustUrl: String?,
    @SerializedName("character")
    val character: Character?,
    @SerializedName("_links")
    val links: Links?,
    @SerializedName("render_url")
    val renderUrl: String?
) {
    data class Character(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("key")
        val key: Key?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("realm")
        val realm: Realm?
    ) {
        data class Key(
            @SerializedName("href")
            val href: String?
        )

        data class Realm(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("key")
            val key: Key?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("slug")
            val slug: String?
        ) {
            data class Key(
                @SerializedName("href")
                val href: String?
            )
        }
    }

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