package fr.jbme.raiderioapp.service.model.blizzard


import com.google.gson.annotations.SerializedName

data class AccountProfile(
    @SerializedName("collections")
    val collections: Collections?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("_links")
    val links: Links?,
    @SerializedName("wow_accounts")
    val wowAccounts: List<WowAccount?>?
) {
    data class Collections(
        @SerializedName("href")
        val href: String?
    )

    data class Links(
        @SerializedName("profile")
        val profile: Profile?,
        @SerializedName("self")
        val self: Self?,
        @SerializedName("user")
        val user: User?
    ) {
        data class Profile(
            @SerializedName("href")
            val href: String?
        )

        data class Self(
            @SerializedName("href")
            val href: String?
        )

        data class User(
            @SerializedName("href")
            val href: String?
        )
    }

    data class WowAccount(
        @SerializedName("characters")
        val characters: List<Character?>?,
        @SerializedName("id")
        val id: Int?
    ) {
        data class Character(
            @SerializedName("character")
            val character: Character?,
            @SerializedName("faction")
            val faction: Faction?,
            @SerializedName("gender")
            val gender: Gender?,
            @SerializedName("id")
            val id: Int?,
            @SerializedName("level")
            val level: Int?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("playable_class")
            val playableClass: PlayableClass?,
            @SerializedName("playable_race")
            val playableRace: PlayableRace?,
            @SerializedName("protected_character")
            val protectedCharacter: ProtectedCharacter?,
            @SerializedName("realm")
            val realm: Realm?
        ) {
            data class Character(
                @SerializedName("href")
                val href: String?
            )

            data class Faction(
                @SerializedName("name")
                val name: String?,
                @SerializedName("type")
                val type: String?
            )

            data class Gender(
                @SerializedName("name")
                val name: String?,
                @SerializedName("type")
                val type: String?
            )

            data class PlayableClass(
                @SerializedName("id")
                val id: Int?,
                @SerializedName("key")
                val key: Key?,
                @SerializedName("name")
                val name: String?
            ) {
                data class Key(
                    @SerializedName("href")
                    val href: String?
                )
            }

            data class PlayableRace(
                @SerializedName("id")
                val id: Int?,
                @SerializedName("key")
                val key: Key?,
                @SerializedName("name")
                val name: String?
            ) {
                data class Key(
                    @SerializedName("href")
                    val href: String?
                )
            }

            data class ProtectedCharacter(
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
    }
}