package fr.jbme.raiderioapp.service.network.retrofit.utils

import okhttp3.Interceptor
import okhttp3.Response

class BlizzardQueryParamsInterceptor(private val locale: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val odlUrl = request.url()

        when (request.header("Namespace")) {
            "Static" -> {
                request = request.newBuilder()
                    .removeHeader("Namespace")
                    .url(
                        odlUrl.newBuilder()
                            .addQueryParameter(NAMESPACE, namespaceStatic)
                            .addQueryParameter(LOCALE, locale)
                            .build()
                    )
                    .build()
            }
            "Dynamic" -> {
                request = request.newBuilder()
                    .removeHeader("Namespace")
                    .url(
                        odlUrl.newBuilder()
                            .addQueryParameter(NAMESPACE, namespaceDynamic)
                            .addQueryParameter(LOCALE, locale)
                            .build()
                    )
                    .build()
            }
            "Profile" -> {
                request = request.newBuilder()
                    .removeHeader("Namespace")
                    .url(
                        odlUrl.newBuilder()
                            .addQueryParameter(NAMESPACE, namespaceProfile)
                            .addQueryParameter(LOCALE, locale)
                            .build()
                    )
                    .build()
            }
        }
        return chain.proceed(request)
    }

    companion object {
        private const val NAMESPACE = "namespace"
        private const val LOCALE = "locale"

        private const val namespaceProfile = "profile-eu"
        private const val namespaceStatic = "static-eu"
        private const val namespaceDynamic = "dynamic-eu"
    }
}