package fr.jbme.raiderioapp.network

import fr.jbme.raiderioapp.data.model.LoggedInUser
import fr.jbme.raiderioapp.data.model.character.CharacterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    private var user: LoggedInUser? = null

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = null
    }

    fun logout() {
        user = null
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
                    val errorResponse = NetworkErrorUtils.parseError(response)
                    callback.onFailure(call, Exception(errorResponse.message))
                }
            }
        })
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // TODO: add user to session
    }
}
