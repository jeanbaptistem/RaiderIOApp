package fr.jbme.raiderioapp.service.network.retrofitUtils

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
    private var tokenInterceptorClient: OkHttpClient? = null
    val tokenInterceptorClientInstance: OkHttpClient
        get() {
            if (tokenInterceptorClient == null) {
                tokenInterceptorClient = baseClientInstance.newBuilder()
                    .addInterceptor(BearerTokenInterceptor())
                    .build()
            }
            return tokenInterceptorClient!!
        }
}