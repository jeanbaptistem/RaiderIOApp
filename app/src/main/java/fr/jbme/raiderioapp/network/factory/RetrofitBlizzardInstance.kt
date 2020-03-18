package fr.jbme.raiderioapp.network.factory

import fr.jbme.raiderioapp.data.BLIZZARD_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBlizzardInstance {
    private var retrofit: Retrofit? = null
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BLIZZARD_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}