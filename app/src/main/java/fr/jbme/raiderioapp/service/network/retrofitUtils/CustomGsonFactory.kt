package fr.jbme.raiderioapp.service.network.retrofitUtils

import com.google.gson.Gson
import retrofit2.converter.gson.GsonConverterFactory

object CustomGsonFactory {
    private val gson = Gson()
    private var gsonFactoryInstance: GsonConverterFactory? = null
    val gsonFactory: GsonConverterFactory
        get() {
            if (gsonFactoryInstance == null) {
                gsonFactoryInstance = GsonConverterFactory.create(
                    gson
                )
            }
            return gsonFactoryInstance!!
        }
}