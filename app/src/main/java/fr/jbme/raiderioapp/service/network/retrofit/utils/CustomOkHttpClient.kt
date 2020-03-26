package fr.jbme.raiderioapp.service.network.retrofit.utils

import fr.jbme.raiderioapp.DEFAULT_LOCALE
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object CustomOkHttpClient {
    private var locale: String = DEFAULT_LOCALE
    private var baseClient: OkHttpClient? = null
    val baseClientInstance: OkHttpClient
        get() {
            if (baseClient == null) {
                baseClient = OkHttpClient.Builder()
                    .readTimeout(40, TimeUnit.SECONDS)
                    .writeTimeout(40, TimeUnit.SECONDS)
                    .addInterceptor(LoggerInterceptor())
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
                        .addInterceptor(BlizzardQueryParamsInterceptor(locale))
                        .addInterceptor(BearerTokenInterceptor())

                        .build()
            }
            return blizzardClient!!
        }

    fun setLocale(locale: String) {
        this.locale = locale
        blizzardClient = null
    }
}