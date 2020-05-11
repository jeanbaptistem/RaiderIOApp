package fr.jbme.raiderioapp.service.network.retrofit

import fr.jbme.raiderioapp.service.network.retrofit.utils.CustomOkHttpClient
import fr.jbme.raiderioapp.service.network.retrofit.utils.WoWProgressResponseConverterFactory
import retrofit2.Retrofit

object RetrofitWoWProgressInstance {
    private var retrofit: Retrofit? = null
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://www.wowprogress.com/")
                    .client(CustomOkHttpClient.clientInstance)
                    .addConverterFactory(WoWProgressResponseConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}
