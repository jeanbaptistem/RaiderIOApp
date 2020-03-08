package fr.jbme.raiderioapp.network.login

import android.content.Context
import android.content.SharedPreferences
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.data.contants.CHARACTER_NAME_KEY
import fr.jbme.raiderioapp.data.contants.REALM_NAME_KEY
import fr.jbme.raiderioapp.data.contants.REGION_KEY
import fr.jbme.raiderioapp.data.contants.SHARED_PREF_KEY
import fr.jbme.raiderioapp.data.model.character.CharacterResponse
import fr.jbme.raiderioapp.data.model.login.LoggedInUser
import fr.jbme.raiderioapp.network.utils.APIException
import fr.jbme.raiderioapp.network.utils.NetworkErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    private var sharedPref: SharedPreferences? = null

    private var user: LoggedInUser? = null

    private val isLoggedIn: Boolean
        get() = user != null

    init {
        sharedPref =
            RaiderIOApp.context?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        user = null
    }

    fun logout() {
        logOutUser()
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
                            character.realm,
                            character.name,
                            character.region
                        )
                    )
                    callback.onResponse(call, response)
                } else {
                    val errorResponse =
                        NetworkErrorUtils.parseError(
                            response
                        )
                    callback.onFailure(
                        call,
                        APIException(errorResponse.statusCode, errorResponse.message)
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

    private fun logOutUser() {
        this.user = null
        with(sharedPref?.edit()) {
            this?.remove(REALM_NAME_KEY)
            this?.remove(CHARACTER_NAME_KEY)
            this?.remove(REGION_KEY)
            this?.commit()
        }
    }
}
