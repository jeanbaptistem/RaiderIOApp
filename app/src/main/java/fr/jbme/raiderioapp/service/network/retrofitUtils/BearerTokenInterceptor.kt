package fr.jbme.raiderioapp.service.network.retrofitUtils

import android.content.Context
import android.util.Log
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
        }
        Log.i("RequestHeaders", request.headers().toString())
        Log.i("RequestURL", request.url().toString())
        return chain.proceed(request)
    }

    private fun getTokenFromSharedPreference(): String? {
        val sharedPref =
            RaiderIOApp.context?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        return sharedPref?.getString(BEARER_TOKEN_KEY, null)
    }

}