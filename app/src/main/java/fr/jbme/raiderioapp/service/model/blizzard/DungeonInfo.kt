package fr.jbme.raiderioapp.service.model.blizzard


import com.google.gson.annotations.SerializedName

data class DungeonInfo(
    @SerializedName("best_runs")
    val bestRuns: List<BestRun?>?,
    @SerializedName("character")
    val character: Character?,
    @SerializedName("_links")
    val links: Links?,
    @SerializedName("season")
    val season: Season?
) {
    data class BestRun(
        @SerializedName("completed_timestamp")
        val completedTimestamp: Long?,
        @SerializedName("dungeon")
        val dungeon: Dungeon?,
        @SerializedName("duration")
        val duration: Int?,
        @SerializedName("is_completed_within_time")
        val isCompletedWithinTime: Boolean?,
        @SerializedName("keystone_affixes")
        val keystoneAffixes: List<KeystoneAffixe?>?,
        @SerializedName("keystone_level")
        val keystoneLevel: Int?,
        @SerializedName("members")
        val members: List<Member?>?
    ) {
        data class Dungeon(
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

        data class KeystoneAffixe(
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

        data class Member(
            @SerializedName("character")
            val character: Character?,
            @SerializedName("equipped_item_level")
            val equippedItemLevel: Int?,
            @SerializedName("race")
            val race: Race?,
            @SerializedName("specialization")
            val specialization: Specialization?
        ) {
            data class Character(
                @SerializedName("id")
                val id: Int?,
                @SerializedName("name")
                val name: String?,
                @SerializedName("realm")
                val realm: Realm?
            ) {
                data class Realm(
                    @SerializedName("id")
                    val id: Int?,
                    @SerializedName("key")
                    val key: Key?,
                    @SerializedName("slug")
                    val slug: String?
                ) {
                    data class Key(
                        @SerializedName("href")
                        val href: String?
                    )
                }
            }

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

            data class Specialization(
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
        }
    }

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

    data class Season(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("key")
        val key: Key?
    ) {
        data class Key(
            @SerializedName("href")
            val href: String?
        )
    }
}