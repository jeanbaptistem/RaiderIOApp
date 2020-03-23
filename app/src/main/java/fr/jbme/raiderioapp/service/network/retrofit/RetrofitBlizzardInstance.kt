package fr.jbme.raiderioapp.service.network.retrofit

import fr.jbme.raiderioapp.REGION
import fr.jbme.raiderioapp.service.network.retrofitUtils.CustomGsonFactory
import fr.jbme.raiderioapp.service.network.retrofitUtils.CustomOkHttpClient
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