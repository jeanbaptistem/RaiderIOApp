package fr.jbme.raiderioapp.service.model.blizzard


import com.google.gson.annotations.SerializedName

data class CharacterProfile(
    @SerializedName("achievement_points")
    val achievementPoints: Int?,
    @SerializedName("achievements")
    val achievements: Achievements?,
    @SerializedName("achievements_statistics")
    val achievementsStatistics: AchievementsStatistics?,
    @SerializedName("active_spec")
    val activeSpec: ActiveSpec?,
    @SerializedName("active_title")
    val activeTitle: ActiveTitle?,
    @SerializedName("appearance")
    val appearance: Appearance?,
    @SerializedName("average_item_level")
    val averageItemLevel: Int?,
    @SerializedName("character_class")
    val characterClass: CharacterClass?,
    @SerializedName("collections")
    val collections: Collections?,
    @SerializedName("encounters")
    val encounters: Encounters?,
    @SerializedName("equipment")
    val equipment: Equipment?,
    @SerializedName("equipped_item_level")
    val equippedItemLevel: Int?,
    @SerializedName("experience")
    val experience: Int?,
    @SerializedName("faction")
    val faction: Faction?,
    @SerializedName("gender")
    val gender: Gender?,
    @SerializedName("guild")
    val guild: Guild?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_login_timestamp")
    val lastLoginTimestamp: Long?,
    @SerializedName("level")
    val level: Int?,
    @SerializedName("_links")
    val links: Links?,
    @SerializedName("media")
    val media: Media?,
    @SerializedName("mythic_keystone_profile")
    val mythicKeystoneProfile: MythicKeystoneProfile?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("pvp_summary")
    val pvpSummary: PvpSummary?,
    @SerializedName("quests")
    val quests: Quests?,
    @SerializedName("race")
    val race: Race?,
    @SerializedName("realm")
    val realm: Realm?,
    @SerializedName("reputations")
    val reputations: Reputations?,
    @SerializedName("specializations")
    val specializations: Specializations?,
    @SerializedName("statistics")
    val statistics: Statistics?,
    @SerializedName("titles")
    val titles: Titles?
) {
    data class Achievements(
        @SerializedName("href")
        val href: String?
    )

    data class AchievementsStatistics(
        @SerializedName("href")
        val href: String?
    )

    data class ActiveSpec(
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

    data class ActiveTitle(
        @SerializedName("display_string")
        val displayString: String?,
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

    data class Appearance(
        @SerializedName("href")
        val href: String?
    )

    data class CharacterClass(
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

    data class Collections(
        @SerializedName("href")
        val href: String?
    )

    data class Encounters(
        @SerializedName("href")
        val href: String?
    )

    data class Equipment(
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

    data class Guild(
        @SerializedName("faction")
        val faction: Faction?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("key")
        val key: Key?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("realm")
        val realm: Realm?
    ) {
        data class Faction(
            @SerializedName("name")
            val name: String?,
            @SerializedName("type")
            val type: String?
        )

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

    data class Media(
        @SerializedName("href")
        val href: String?
    )

    data class MythicKeystoneProfile(
        @SerializedName("href")
        val href: String?
    )

    data class PvpSummary(
        @SerializedName("href")
        val href: String?
    )

    data class Quests(
        @SerializedName("href")
        val href: String?
    )

    data class Race(
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

    data class Reputations(
        @SerializedName("href")
        val href: String?
    )

    data class Specializations(
        @SerializedName("href")
        val href: String?
    )

    data class Statistics(
        @SerializedName("href")
        val href: String?
    )

    data class Titles(
        @SerializedName("href")
        val href: String?
    )
}