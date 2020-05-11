package fr.jbme.raiderioapp.service.network.retrofit.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import fr.jbme.raiderioapp.service.model.raiderio.CharacterRanks
import retrofit2.converter.gson.GsonConverterFactory

object CustomGsonFactory {
    private val gson = Gson()
    private var gsonFactoryBaseInstance: GsonConverterFactory? = null
    val gsonFactoryBase: GsonConverterFactory
        get() {
            if (gsonFactoryBaseInstance == null) {
                gsonFactoryBaseInstance = GsonConverterFactory
                    .create(gson)
            }
            return gsonFactoryBaseInstance!!
        }

    private var gsonFactoryCustomInstance: GsonConverterFactory? = null
    val gsonFactoryCustom: GsonConverterFactory
        get() {
            if (gsonFactoryCustomInstance == null) {
                gsonFactoryCustomInstance = GsonConverterFactory
                    .create(customGson())
            }
            return gsonFactoryCustomInstance!!
        }

    private fun customGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(CharacterRanks::class.java, dungeonsRanksDeserializer)
        return gsonBuilder.create()
    }

    private val dungeonsRanksDeserializer =
        JsonDeserializer { json, _, _ ->
            val jsonObject = json?.asJsonObject
            val listOfRanks = mutableListOf<CharacterRanks.Rank>()
            jsonObject?.get("mythic_plus_ranks")?.asJsonObject?.entrySet()
                ?.forEach {
                    val value = it.value.asJsonObject
                    listOfRanks.add(
                        CharacterRanks.Rank(
                            formatName(it.key, jsonObject),
                            value.get("world").asInt,
                            value.get("region").asInt,
                            value.get("realm").asInt
                        )
                    )
                }

            CharacterRanks(
                jsonObject?.get("achievement_points")?.asInt,
                jsonObject?.get("active_spec_name")?.asString,
                jsonObject?.get("active_spec_role")?.asString,
                jsonObject?.get("class")?.asString,
                jsonObject?.get("faction")?.asString,
                jsonObject?.get("gender")?.asString,
                jsonObject?.get("honorable_kills")?.asInt,
                listOfRanks.toList(),
                jsonObject?.get("name")?.asString,
                jsonObject?.get("profile_banner")?.asString,
                jsonObject?.get("profile_url")?.asString,
                jsonObject?.get("race")?.asString,
                jsonObject?.get("realm")?.asString,
                jsonObject?.get("region")?.asString,
                jsonObject?.get("thumbnail_url")?.asString
            )
        }

    private fun formatName(rankKey: String, character: JsonObject): String {
        var res = rankKey
        val _class = character.get("class").asString
        val faction = character.get("faction").asString
        res += if (rankKey.contains("class")) "_${_class}" else ""
        res += if (rankKey.contains("faction")) "_${faction}" else ""
        return res
    }
}