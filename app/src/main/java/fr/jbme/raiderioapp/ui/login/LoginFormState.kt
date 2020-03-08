package fr.jbme.raiderioapp.ui.login

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val realmNameError: Int? = null,
    val characterNameError: Int? = null,
    val isDataValid: Boolean = false
)
