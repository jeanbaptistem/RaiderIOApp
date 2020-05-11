package fr.jbme.raiderioapp.service.network.retrofit.utils

import fr.jbme.raiderioapp.service.model.wowprogress.WoWProgressResponse
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.util.*

class WoWProgressResponseConverterFactory private constructor() : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return if (WoWProgressResponse::class.java.isAssignableFrom(type as Class<*>)) {
            WoWProgressResponseConverter()
        } else null
    }

    internal class WoWProgressResponseConverter :
        Converter<ResponseBody, WoWProgressResponse> {

        override fun convert(responseBody: ResponseBody): WoWProgressResponse {
            val characterList = mutableListOf<WoWProgressResponse.Characters>()
            val body = responseBody.string()
            val resultList = body.split("Character|")
            resultList.forEach {
                val characterInfoList = it.split("|")
                if (characterInfoList.size == 4) {
                    val name = characterInfoList[1]
                    val realm = characterInfoList[2].split("-")[1]
                    val region = characterInfoList[2].split("-")[0].toLowerCase(Locale.ROOT)
                    val _class = characterInfoList[3]
                    characterList.add(WoWProgressResponse.Characters(name, realm, region, _class))
                }
            }
            return WoWProgressResponse("Character", characterList)
        }

    }

    companion object {
        fun create(): WoWProgressResponseConverterFactory {
            return WoWProgressResponseConverterFactory()
        }
    }
}