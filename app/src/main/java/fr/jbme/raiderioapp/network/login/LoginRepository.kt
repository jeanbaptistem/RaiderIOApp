package fr.jbme.raiderioapp.network.login

import android.content.Context
import android.content.SharedPreferences
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.model.CHARACTER_NAME_KEY
import fr.jbme.raiderioapp.model.REALM_NAME_KEY
import fr.jbme.raiderioapp.model.REGION_KEY
import fr.jbme.raiderioapp.model.RIOCharacter.RIOCharacterResponse
import fr.jbme.raiderioapp.model.SHARED_PREF_KEY
import fr.jbme.raiderioapp.model.login.LoggedInUser
import fr.jbme.raiderioapp.network.utils.NetworkErrorUtils
import fr.jbme.raiderioapp.utils.APIError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {


    private var sharedPref: SharedPreferences? =
        RaiderIOApp.context?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)

    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = null
    }

    fun logout() {
        logoutUser()
        dataSource.logout()
    }

    fun login(
        realmName: String,
        characterName: String,
        region: String,
        callback: Callback<RIOCharacterResponse>
    ) {
        // handle login
        dataSource.login(realmName, characterName, region, object : Callback<RIOCharacterResponse> {

            override fun onFailure(
                call: Call<RIOCharacterResponse>,
                t: Throwable
            ) {
                callback.onFailure(call, t)
            }

            override fun onResponse(
                call: Call<RIOCharacterResponse>,
                response: Response<RIOCharacterResponse>
            ) {
                if (response.isSuccessful) {
                    val character = response.body()!!
                    setLoggedInUser(
                        LoggedInUser(
                            character.realm!!,
                            character.name!!,
                            character.region!!
                        )
                    )
                    callback.onResponse(call, response)
                } else {
                    val errorResponse =
                        NetworkErrorUtils.parseRIOError(response)
                    callback.onFailure(
                        call,
                        APIError(
                            errorResponse.message,
                            errorResponse.statusCode,
                            errorResponse.error
                        )
                    )
                }
            }
        })
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        with(sharedPref?.edit()) {
            this?.putString(REALM_NAME_KEY, user!!.realmName)
            this?.putString(CHARACTER_NAME_KEY, user!!.characterName)
            this?.putString(REGION_KEY, user!!.region)
            this?.commit()
        }
    }

    private fun logoutUser() {
        this.user = null
    }
}
