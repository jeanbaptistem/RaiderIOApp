package fr.jbme.raiderioapp.network.login

import fr.jbme.raiderioapp.model.RIOCharacter.RIOCharacterResponse
import fr.jbme.raiderioapp.network.factory.RetrofitRaiderIOInstance
import fr.jbme.raiderioapp.network.services.RaiderIOService
import retrofit2.Callback

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    private var raiderIOService: RaiderIOService? =
        RetrofitRaiderIOInstance.retrofitInstance?.create(
            RaiderIOService::class.java
        )

    fun login(
        realmName: String,
        characterName: String,
        region: String,
        callback: Callback<RIOCharacterResponse>
    ) {
        val call = raiderIOService?.getCharacterInfo(region, realmName, characterName)
        call?.enqueue(callback)
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

