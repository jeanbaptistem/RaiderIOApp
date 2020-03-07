package fr.jbme.raiderioapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.data.LoginRepository
import fr.jbme.raiderioapp.data.Result
import fr.jbme.raiderioapp.data.model.LoggedInUser

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(realmName: String, characterName: String, region: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(realmName, characterName, region)

        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(
                    success = LoggedInUser(
                        result.data.realmName,
                        result.data.characterName,
                        result.data.region
                    )
                )
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(realmName: String, characterName: String, region: String) {
        if (!isRealmNameValid(realmName)) {
            _loginForm.value = LoginFormState(realmNameError = R.string.invalid_realm_name)
        } else if (!isCharacterNameValid(characterName)) {
            _loginForm.value = LoginFormState(characterNameError = R.string.invalid_character_name)
        } else if (!isRegionValid(region)) {
            _loginForm.value = LoginFormState(characterNameError = R.string.invalid_region)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isRealmNameValid(realmName: String): Boolean {
        return true
    }

    // A placeholder password validation check
    private fun isCharacterNameValid(characterName: String): Boolean {
        return true
    }

    // A placeholder username validation check
    private fun isRegionValid(region: String): Boolean {
        return true
    }
}
