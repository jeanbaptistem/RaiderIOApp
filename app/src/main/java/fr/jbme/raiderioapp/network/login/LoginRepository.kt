package fr.jbme.raiderioapp.network.login

import android.content.Context
import androidx.lifecycle.LiveData
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.model.BEARER_TOKEN_KEY
import fr.jbme.raiderioapp.model.SHARED_PREF_KEY
import fr.jbme.raiderioapp.model.blizzard.login.AccessTokenResponse
import fr.jbme.raiderioapp.model.login.LoggedInUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
class LoginRepository(private val dataSource: LoginDataSource) {

    private val sharedPref =
        RaiderIOApp.context?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)

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
    }

    fun login(code: String): LiveData<AccessTokenResponse> {
        dataSource.login(code)
        dataSource.accessToken.observeForever {
            setLoggedInUser(LoggedInUser(it.access_token))
        }
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
