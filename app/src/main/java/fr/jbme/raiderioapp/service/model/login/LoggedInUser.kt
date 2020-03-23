package fr.jbme.raiderioapp.service.model.login

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val accessToken: String
) {
    override fun toString(): String {
        return "LoggedInUser(accessToken='$accessToken')"
    }
}
