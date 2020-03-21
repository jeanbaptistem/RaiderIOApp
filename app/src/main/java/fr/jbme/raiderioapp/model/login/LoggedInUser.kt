package fr.jbme.raiderioapp.model.login

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val realmName: String,
    val characterName: String,
    val region: String
) {
    override fun toString(): String {
        return "LoggedInUser(realmName='$realmName', characterName='$characterName', region='$region')"
    }
}
