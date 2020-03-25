package fr.jbme.raiderioapp.service.network.retrofit.utils

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object CustomOkHttpClient {
    private var baseClient: OkHttpClient? = null
    val baseClientInstance: OkHttpClient
        get() {
            if (baseClient == null) {
                baseClient = OkHttpClient.Builder()
                    .readTimeout(40, TimeUnit.SECONDS)
                    .writeTimeout(40, TimeUnit.SECONDS)
                    .build()
            }
            return baseClient!!
        }
    private var blizzardClient: OkHttpClient? = null
    val blizzardClientInstance: OkHttpClient
        get() {
            if (blizzardClient == null) {
                blizzardClient =
                    baseClientInstance.newBuilder()
                        .addInterceptor(BlizzardQueryParamsInterceptor())
                        .addInterceptor(BearerTokenInterceptor())
                        .addInterceptor(LoggerInterceptor())
                        .build()
            }
            return blizzardClient!!
        }
}