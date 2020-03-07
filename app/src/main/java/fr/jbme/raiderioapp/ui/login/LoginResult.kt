package fr.jbme.raiderioapp.ui.login

import fr.jbme.raiderioapp.data.model.LoggedInUser

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUser? = null,
    val error: Int? = null
)
