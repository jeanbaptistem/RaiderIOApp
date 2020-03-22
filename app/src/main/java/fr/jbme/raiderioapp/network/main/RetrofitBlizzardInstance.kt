package fr.jbme.raiderioapp.network.main

import fr.jbme.raiderioapp.model.REGION
import fr.jbme.raiderioapp.network.retrofit.CustomGsonFactory
import fr.jbme.raiderioapp.network.retrofit.CustomOkHttpClient
import retrofit2.Retrofit

object RetrofitBlizzardInstance {
    private var retrofit: Retrofit? = null
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://${REGION}.api.blizzard.com/")
                    .client(CustomOkHttpClient.tokenIntercepClientInstance)
                    .addConverterFactory(CustomGsonFactory.gsonFactory)
                    .build()
            }
            return retrofit
        }
}