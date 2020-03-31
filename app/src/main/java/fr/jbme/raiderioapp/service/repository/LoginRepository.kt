package fr.jbme.raiderioapp.service.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import fr.jbme.raiderioapp.BEARER_TOKEN_KEY
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.SHARED_PREF_KEY
import fr.jbme.raiderioapp.service.model.login.AccessTokenResponse
import fr.jbme.raiderioapp.service.model.login.LoggedInUser

class LoginRepository(private val dataSource: LoginDataSource) {

    private val sharedPref =
        RaiderIOApp.context?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)

    private val loginObserver =
        Observer<AccessTokenResponse>(fun(token) { setLoggedInUser(LoggedInUser(token.accessToken)) })

    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = null
    }

    fun logout() {
        this.user = null
        dataSource.logout()
        dataSource.accessToken.removeObserver(loginObserver)
    }

    fun login(code: String): LiveData<AccessTokenResponse> {
        dataSource.login(code)
        dataSource.accessToken.observeForever(loginObserver)
        return dataSource.accessToken
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        if (sharedPref != null) {
            with(sharedPref.edit()) {
                putString(BEARER_TOKEN_KEY, user!!.accessToken)
                commit()
            }
        }
    }
}
