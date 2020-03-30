package fr.jbme.raiderioapp.service.network.retrofit.utils

import fr.jbme.raiderioapp.DEFAULT_LOCALE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object CustomOkHttpClient {
    private var locale: String = DEFAULT_LOCALE
    private val blizInterceptor = BlizzardQueryParamsInterceptor(locale)
    private val tokenInterceptor = BearerTokenInterceptor()
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    }

    private var client: OkHttpClient? = null
    val clientInstance: OkHttpClient
        get() {
            if (client == null) {
                client =
                    OkHttpClient.Builder()
                        .readTimeout(40, TimeUnit.SECONDS)
                        .writeTimeout(40, TimeUnit.SECONDS)
                        .addInterceptor(blizInterceptor)
                        .addInterceptor(tokenInterceptor)
                        .addInterceptor(loggingInterceptor)
                        .build()
            }
            return client!!
        }

    fun setLocale(locale: String) {
        this.locale = locale
        client = null
    }
}