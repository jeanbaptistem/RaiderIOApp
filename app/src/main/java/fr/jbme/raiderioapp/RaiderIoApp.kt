package fr.jbme.raiderioapp

import android.app.Application
import android.content.Context
import fr.jbme.raiderioapp.network.login.LoginDataSource
import fr.jbme.raiderioapp.network.login.LoginRepository

class RaiderIoApp : Application() {
    companion object {
        lateinit var instance: RaiderIoApp
            private set

        val context: Context?
            get() = instance.applicationContext

        val loginRepository: LoginRepository = LoginRepository(dataSource = LoginDataSource())
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}