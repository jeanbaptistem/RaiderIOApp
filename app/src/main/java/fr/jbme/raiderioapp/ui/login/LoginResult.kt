package fr.jbme.raiderioapp.ui.login

import fr.jbme.raiderioapp.model.login.LoggedInUser

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUser? = null,
    val error: String? = null
)
