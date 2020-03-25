package fr.jbme.raiderioapp

import android.app.Application
import android.content.Context
import fr.jbme.raiderioapp.service.repository.LoginDataSource
import fr.jbme.raiderioapp.service.repository.LoginRepository

class RaiderIOApp : Application() {
    companion object {
        lateinit var instance: RaiderIOApp
            private set

        val context: Context?
            get() = instance.applicationContext

        lateinit var loginRepository: LoginRepository
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        loginRepository =
            LoginRepository(dataSource = LoginDataSource())
    }

}