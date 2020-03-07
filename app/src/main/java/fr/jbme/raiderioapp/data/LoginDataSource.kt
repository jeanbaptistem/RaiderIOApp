package fr.jbme.raiderioapp.data

import fr.jbme.raiderioapp.data.model.LoggedInUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(realmName: String, characterName: String, region: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(realmName, characterName, region)
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

