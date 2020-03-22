package fr.jbme.raiderioapp.network.login

import fr.jbme.raiderioapp.model.REGION
import fr.jbme.raiderioapp.network.retrofit.CustomGsonFactory
import fr.jbme.raiderioapp.network.retrofit.CustomOkHttpClient
import retrofit2.Retrofit

object RetrofitBlizzardLoginInstance {
    private var retrofit: Retrofit? = null
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://${REGION}.battle.net/")
                    .client(CustomOkHttpClient.baseClientInstance)
                    .addConverterFactory(CustomGsonFactory.gsonFactory)
                    .build()
            }
            return retrofit
        }
}