package fr.jbme.raiderioapp.service.model.blizzard


import com.google.gson.annotations.SerializedName

data class RaidInfo(
    @SerializedName("character")
    val character: Character?,
    @SerializedName("expansions")
    val expansions: List<Expansion?>?,
    @SerializedName("_links")
    val links: Links?
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

    data class Expansion(
        @SerializedName("expansion")
        val expansion: Expansion?,
        @SerializedName("instances")
        val instances: List<Instance?>?
    ) {
        data class Expansion(
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

        data class Instance(
            @SerializedName("instance")
            val instance: Instance?,
            @SerializedName("modes")
            val modes: List<Mode?>?
        ) {
            data class Instance(
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

            data class Mode(
                @SerializedName("difficulty")
                val difficulty: Difficulty?,
                @SerializedName("progress")
                val progress: Progress?,
                @SerializedName("status")
                val status: Status?
            ) {
                data class Difficulty(
                    @SerializedName("name")
                    val name: String?,
                    @SerializedName("type")
                    val type: String?
                )

                data class Progress(
                    @SerializedName("completed_count")
                    val completedCount: Int?,
                    @SerializedName("encounters")
                    val encounters: List<Encounter?>?,
                    @SerializedName("total_count")
                    val totalCount: Int?
                ) {
                    data class Encounter(
                        @SerializedName("completed_count")
                        val completedCount: Int?,
                        @SerializedName("encounter")
                        val encounter: Encounter?,
                        @SerializedName("last_kill_timestamp")
                        val lastKillTimestamp: Long?
                    ) {
                        data class Encounter(
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

                data class Status(
                    @SerializedName("name")
                    val name: String?,
                    @SerializedName("type")
                    val type: String?
                )
            }
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