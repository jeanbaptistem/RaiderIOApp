package fr.jbme.raiderioapp.network.login

import android.content.Context
import android.content.SharedPreferences
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.data.CHARACTER_NAME_KEY
import fr.jbme.raiderioapp.data.REALM_NAME_KEY
import fr.jbme.raiderioapp.data.REGION_KEY
import fr.jbme.raiderioapp.data.SHARED_PREF_KEY
import fr.jbme.raiderioapp.data.model.character.CharacterResponse
import fr.jbme.raiderioapp.data.model.login.LoggedInUser
import fr.jbme.raiderioapp.network.utils.APIError
import fr.jbme.raiderioapp.network.utils.NetworkErrorUtils
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
        callback: Callback<CharacterResponse>
    ) {
        // handle login
        dataSource.login(realmName, characterName, region, object : Callback<CharacterResponse> {

            override fun onFailure(
                call: Call<CharacterResponse>,
                t: Throwable
            ) {
                callback.onFailure(call, t)
            }

            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
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
                        NetworkErrorUtils.parseError(response)
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
