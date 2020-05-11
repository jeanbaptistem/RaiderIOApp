package fr.jbme.raiderioapp.service.model.blizzard


import com.google.gson.annotations.SerializedName

data class CharacterEquipment(
    @SerializedName("character")
    val character: Character?,
    @SerializedName("equipped_items")
    val equippedItems: List<EquippedItem?>?,
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

    data class EquippedItem(
        @SerializedName("armor")
        val armor: Armor?,
        @SerializedName("azerite_details")
        val azeriteDetails: AzeriteDetails?,
        @SerializedName("binding")
        val binding: Binding?,
        @SerializedName("bonus_list")
        val bonusList: List<Int?>?,
        @SerializedName("context")
        val context: Int?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("durability")
        val durability: Durability?,
        @SerializedName("enchantments")
        val enchantments: List<Enchantment?>?,
        @SerializedName("inventory_type")
        val inventoryType: InventoryType?,
        @SerializedName("is_corrupted")
        val isCorrupted: Boolean?,
        @SerializedName("is_subclass_hidden")
        val isSubclassHidden: Boolean?,
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
        @SerializedName("modified_appearance_id")
        val modifiedAppearanceId: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("name_description")
        val nameDescription: NameDescription?,
        @SerializedName("quality")
        val quality: Quality?,
        @SerializedName("quantity")
        val quantity: Int?,
        @SerializedName("requirements")
        val requirements: Requirements?,
        @SerializedName("sell_price")
        val sellPrice: SellPrice?,
        @SerializedName("slot")
        val slot: Slot?,
        @SerializedName("sockets")
        val sockets: List<Socket?>?,
        @SerializedName("spells")
        val spells: List<Spell?>?,
        @SerializedName("stats")
        val stats: List<Stat?>?,
        @SerializedName("transmog")
        val transmog: Transmog?,
        @SerializedName("unique_equipped")
        val uniqueEquipped: String?,
        @SerializedName("weapon")
        val weapon: Weapon?
    ) {
        data class Armor(
            @SerializedName("display")
            val display: Display?,
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
        }

        data class AzeriteDetails(
            @SerializedName("level")
            val level: Level?,
            @SerializedName("percentage_to_next_level")
            val percentageToNextLevel: Double?,
            @SerializedName("selected_essences")
            val selectedEssences: List<SelectedEssence?>?,
            @SerializedName("selected_powers")
            val selectedPowers: List<SelectedPower?>?,
            @SerializedName("selected_powers_string")
            val selectedPowersString: String?
        ) {
            data class Level(
                @SerializedName("display_string")
                val displayString: String?,
                @SerializedName("value")
                val value: Int?
            )

            data class SelectedEssence(
                @SerializedName("essence")
                val essence: Essence?,
                @SerializedName("main_spell_tooltip")
                val mainSpellTooltip: MainSpellTooltip?,
                @SerializedName("media")
                val media: Media?,
                @SerializedName("passive_spell_tooltip")
                val passiveSpellTooltip: PassiveSpellTooltip?,
                @SerializedName("rank")
                val rank: Int?,
                @SerializedName("slot")
                val slot: Int?
            ) {
                data class Essence(
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

                data class MainSpellTooltip(
                    @SerializedName("cast_time")
                    val castTime: String?,
                    @SerializedName("cooldown")
                    val cooldown: String?,
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

                data class PassiveSpellTooltip(
                    @SerializedName("cast_time")
                    val castTime: String?,
                    @SerializedName("description")
                    val description: String?,
                    @SerializedName("range")
                    val range: String?,
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
            }

            data class SelectedPower(
                @SerializedName("id")
                val id: Int?,
                @SerializedName("is_display_hidden")
                val isDisplayHidden: Boolean?,
                @SerializedName("spell_tooltip")
                val spellTooltip: SpellTooltip?,
                @SerializedName("tier")
                val tier: Int?
            ) {
                data class SpellTooltip(
                    @SerializedName("cast_time")
                    val castTime: String?,
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
            }
        }

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

        data class Enchantment(
            @SerializedName("display_string")
            val displayString: String?,
            @SerializedName("enchantment_id")
            val enchantmentId: Int?,
            @SerializedName("enchantment_slot")
            val enchantmentSlot: EnchantmentSlot?,
            @SerializedName("source_item")
            val sourceItem: SourceItem?
        ) {
            data class EnchantmentSlot(
                @SerializedName("id")
                val id: Int?,
                @SerializedName("type")
                val type: String?
            )

            data class SourceItem(
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

        data class NameDescription(
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

        data class Quality(
            @SerializedName("name")
            val name: String?,
            @SerializedName("type")
            val type: String?
        )

        data class Requirements(
            @SerializedName("faction")
            val faction: Faction?,
            @SerializedName("level")
            val level: Level?,
            @SerializedName("skill")
            val skill: Skill?
        ) {
            data class Faction(
                @SerializedName("display_string")
                val displayString: String?,
                @SerializedName("value")
                val value: Value?
            ) {
                data class Value(
                    @SerializedName("name")
                    val name: String?,
                    @SerializedName("type")
                    val type: String?
                )
            }

            data class Level(
                @SerializedName("display_string")
                val displayString: String?,
                @SerializedName("value")
                val value: Int?
            )

            data class Skill(
                @SerializedName("display_string")
                val displayString: String?,
                @SerializedName("level")
                val level: Int?,
                @SerializedName("profession")
                val profession: Profession?
            ) {
                data class Profession(
                    @SerializedName("id")
                    val id: Int?,
                    @SerializedName("name")
                    val name: String?
                )
            }
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

        data class Slot(
            @SerializedName("name")
            val name: String?,
            @SerializedName("type")
            val type: String?
        )

        data class Socket(
            @SerializedName("display_string")
            val displayString: String?,
            @SerializedName("item")
            val item: Item?,
            @SerializedName("media")
            val media: Media?,
            @SerializedName("socket_type")
            val socketType: SocketType?
        ) {
            data class Item(
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

            data class SocketType(
                @SerializedName("name")
                val name: String?,
                @SerializedName("type")
                val type: String?
            )
        }

        data class Spell(
            @SerializedName("description")
            val description: String?,
            @SerializedName("display_color")
            val displayColor: DisplayColor?,
            @SerializedName("spell")
            val spell: Spell?
        ) {
            data class DisplayColor(
                @SerializedName("a")
                val a: Double?,
                @SerializedName("b")
                val b: Int?,
                @SerializedName("g")
                val g: Int?,
                @SerializedName("r")
                val r: Int?
            )

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
            @SerializedName("is_equip_bonus")
            val isEquipBonus: Boolean?,
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

        data class Transmog(
            @SerializedName("display_string")
            val displayString: String?,
            @SerializedName("item")
            val item: Item?,
            @SerializedName("item_modified_appearance_id")
            val itemModifiedAppearanceId: Int?
        ) {
            data class Item(
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