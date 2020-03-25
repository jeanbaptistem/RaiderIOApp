package fr.jbme.raiderioapp.service.network.retrofit.utils

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LoggerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        Log.i(
            LOGGER_TAG,
            "Method: " + request.method() + "\n" +
                    request.headers().toString() +
                    "URL: " + request.url().toString() + "\n" +
                    "Body: " + request.body().toString()
        )
        return chain.proceed(request)
    }

    companion object {
        private const val LOGGER_TAG = "NetworkLogger"
    }
}