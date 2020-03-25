package fr.jbme.raiderioapp.service.network.retrofit.utils

import android.content.Context
import fr.jbme.raiderioapp.BEARER_TOKEN_KEY
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.SHARED_PREF_KEY
import okhttp3.Interceptor
import okhttp3.Response

class BearerTokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (request.header("No-Authentication") == null) {
            val token = getTokenFromSharedPreference()
            if (!token.isNullOrEmpty()) {
                val finalToken = "Bearer $token"
                request = request.newBuilder()
                    .addHeader("Authorization", finalToken)
                    .build()

            }
        } else {
            request = request.newBuilder()
                .removeHeader("No-Authentication")
                .build()
        }
        return chain.proceed(request)
    }

    private fun getTokenFromSharedPreference(): String? {
        val sharedPref =
            RaiderIOApp.context?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        return sharedPref?.getString(BEARER_TOKEN_KEY, null)
    }

}