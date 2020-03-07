package fr.jbme.raiderioapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.data.model.LoggedInUser
import fr.jbme.raiderioapp.data.model.character.CharacterResponse
import fr.jbme.raiderioapp.network.LoginRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(realmName: String, characterName: String, region: String) {
        // can be launched in a separate asynchronous job
        loginRepository.login(
            realmName,
            characterName,
            region,
            object : Callback<CharacterResponse> {
                override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                    _loginResult.value =
                        LoginResult(error = t.message)
                }

                override fun onResponse(
                    call: Call<CharacterResponse>,
                    response: Response<CharacterResponse>
                ) {
                    val body = response.body()!!
                    _loginResult.value =
                        LoginResult(success = LoggedInUser(body.realm, body.name, body.region))
                }
            })
    }

    fun logout() {
        loginRepository.logout()
    }

    fun loginDataChanged(realmName: String, characterName: String, region: String) {
        if (!isRealmNameValid(realmName)) {
            _loginForm.value = LoginFormState(realmNameError = R.string.invalid_realm_name)
        } else if (!isCharacterNameValid(characterName)) {
            _loginForm.value = LoginFormState(characterNameError = R.string.invalid_character_name)
        } else if (!isRegionValid(region)) {
            _loginForm.value = LoginFormState(regionIsSelected = true)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder realm name validation check
    private fun isRealmNameValid(realmName: String): Boolean {
        return realmName.matches(Regex("[a-zA-Z]+"))
    }

    // A placeholder character name validation check
    private fun isCharacterNameValid(characterName: String): Boolean {
        return characterName.matches(Regex("[a-zA-Z]+"))
    }

    // A placeholder region validation check
    private fun isRegionValid(region: String): Boolean {
        return true
    }
}
