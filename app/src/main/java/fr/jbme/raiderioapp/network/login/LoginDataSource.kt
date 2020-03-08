package fr.jbme.raiderioapp.network.login

import fr.jbme.raiderioapp.data.model.character.CharacterResponse
import fr.jbme.raiderioapp.network.RaiderIOService
import fr.jbme.raiderioapp.network.RetrofitInstance
import retrofit2.Callback

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    private var raiderIOService: RaiderIOService? =
        RetrofitInstance.retrofitInstance?.create(
            RaiderIOService::class.java
        )

    fun login(
        realmName: String,
        characterName: String,
        region: String,
        callback: Callback<CharacterResponse>
    ) {
        val call = raiderIOService?.getCharacterInfo(region, realmName, characterName)
        call?.enqueue(callback)
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

