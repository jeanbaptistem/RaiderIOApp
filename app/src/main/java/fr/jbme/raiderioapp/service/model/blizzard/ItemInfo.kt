package fr.jbme.raiderioapp.service.model.blizzard


import com.google.gson.annotations.SerializedName

data class ItemInfo(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("inventory_type")
    val inventoryType: InventoryType?,
    @SerializedName("is_equippable")
    val isEquippable: Boolean?,
    @SerializedName("is_stackable")
    val isStackable: Boolean?,
    @SerializedName("item_class")
    val itemClass: ItemClass?,
    @SerializedName("item_subclass")
    val itemSubclass: ItemSubclass?,
    @SerializedName("level")
    val level: Int?,
    @SerializedName("_links")
    val links: Links?,
    @SerializedName("max_count")
    val maxCount: Int?,
    @SerializedName("media")
    val media: Media?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("preview_item")
    val previewItem: PreviewItem?,
    @SerializedName("purchase_price")
    val purchasePrice: Int?,
    @SerializedName("quality")
    val quality: Quality?,
    @SerializedName("required_level")
    val requiredLevel: Int?,
    @SerializedName("sell_price")
    val sellPrice: Int?
) {
    data class InventoryType(
        @SerializedName("name")
        val name: String?,
        @SerializedName("type")
        val type: String?
    )

    data class ItemClass(
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

    data class ItemSubclass(
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

    data class PreviewItem(
        @SerializedName("binding")
        val binding: Binding?,
        @SerializedName("durability")
        val durability: Durability?,
        @SerializedName("inventory_type")
        val inventoryType: InventoryType?,
        @SerializedName("item")
        val item: Item?,
        @SerializedName("item_class")
        val itemClass: ItemClass?,
        @SerializedName("item_subclass")
        val itemSubclass: ItemSubclass?,
        @SerializedName("level")
        val level: Level?,
        @SerializedName("media")
        val media: Media?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("quality")
        val quality: Quality?,
        @SerializedName("requirements")
        val requirements: Requirements?,
        @SerializedName("sell_price")
        val sellPrice: SellPrice?,
        @SerializedName("spells")
        val spells: List<Spell?>?,
        @SerializedName("stats")
        val stats: List<Stat?>?,
        @SerializedName("unique_equipped")
        val uniqueEquipped: String?,
        @SerializedName("weapon")
        val weapon: Weapon?
    ) {
        data class Binding(
            @SerializedName("name")
            val name: String?,
            @SerializedName("type")
            val type: String?
        )

        data class Durability(
            @SerializedName("display_string")
            val displayString: String?,
            @SerializedName("value")
            val value: Int?
        )

        data class InventoryType(
            @SerializedName("name")
            val name: String?,
            @SerializedName("type")
            val type: String?
        )

        data class Item(
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

        data class ItemClass(
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

        data class ItemSubclass(
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

        data class Level(
            @SerializedName("display_string")
            val displayString: String?,
            @SerializedName("value")
            val value: Int?
        )

        data class Media(
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

        data class Quality(
            @SerializedName("name")
            val name: String?,
            @SerializedName("type")
            val type: String?
        )

        data class Requirements(
            @SerializedName("level")
            val level: Level?
        ) {
            data class Level(
                @SerializedName("display_string")
                val displayString: String?,
                @SerializedName("value")
                val value: Int?
            )
        }

        data class SellPrice(
            @SerializedName("display_strings")
            val displayStrings: DisplayStrings?,
            @SerializedName("value")
            val value: Int?
        ) {
            data class DisplayStrings(
                @SerializedName("copper")
                val copper: String?,
                @SerializedName("gold")
                val gold: String?,
                @SerializedName("header")
                val header: String?,
                @SerializedName("silver")
                val silver: String?
            )
        }

        data class Spell(
            @SerializedName("description")
            val description: String?,
            @SerializedName("spell")
            val spell: Spell?
        ) {
            data class Spell(
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

        data class Stat(
            @SerializedName("display")
            val display: Display?,
            @SerializedName("is_negated")
            val isNegated: Boolean?,
            @SerializedName("type")
            val type: Type?,
            @SerializedName("value")
            val value: Int?
        ) {
            data class Display(
                @SerializedName("color")
                val color: Color?,
                @SerializedName("display_string")
                val displayString: String?
            ) {
                data class Color(
                    @SerializedName("a")
                    val a: Double?,
                    @SerializedName("b")
                    val b: Int?,
                    @SerializedName("g")
                    val g: Int?,
                    @SerializedName("r")
                    val r: Int?
                )
            }

            data class Type(
                @SerializedName("name")
                val name: String?,
                @SerializedName("type")
                val type: String?
            )
        }

        data class Weapon(
            @SerializedName("attack_speed")
            val attackSpeed: AttackSpeed?,
            @SerializedName("damage")
            val damage: Damage?,
            @SerializedName("dps")
            val dps: Dps?
        ) {
            data class AttackSpeed(
                @SerializedName("display_string")
                val displayString: String?,
                @SerializedName("value")
                val value: Int?
            )

            data class Damage(
                @SerializedName("damage_class")
                val damageClass: DamageClass?,
                @SerializedName("display_string")
                val displayString: String?,
                @SerializedName("max_value")
                val maxValue: Int?,
                @SerializedName("min_value")
                val minValue: Int?
            ) {
                data class DamageClass(
                    @SerializedName("name")
                    val name: String?,
                    @SerializedName("type")
                    val type: String?
                )
            }

            data class Dps(
                @SerializedName("display_string")
                val displayString: String?,
                @SerializedName("value")
                val value: Double?
            )
        }
    }

    data class Quality(
        @SerializedName("name")
        val name: String?,
        @SerializedName("type")
        val type: String?
    )
}