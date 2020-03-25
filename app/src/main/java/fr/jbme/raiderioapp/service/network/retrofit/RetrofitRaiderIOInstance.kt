package fr.jbme.raiderioapp.service.network.retrofit

import fr.jbme.raiderioapp.service.network.retrofit.utils.CustomGsonFactory
import fr.jbme.raiderioapp.service.network.retrofit.utils.CustomOkHttpClient
import retrofit2.Retrofit

object RetrofitRaiderIOInstance {
    private var retrofit: Retrofit? = null
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://raider.io/api/v1/")
                    .client(CustomOkHttpClient.baseClientInstance)
                    .addConverterFactory(CustomGsonFactory.gsonFactory)
                    .build()
            }
            return retrofit
        }
}

