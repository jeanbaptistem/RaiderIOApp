package fr.jbme.raiderioapp

import android.app.Application
import android.content.Context

class RaiderIOApp : Application() {
    companion object {
        var instance: RaiderIOApp? = null
            private set

        val context: Context?
            get() = instance?.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}