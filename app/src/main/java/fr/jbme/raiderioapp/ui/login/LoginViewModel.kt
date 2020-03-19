package fr.jbme.raiderioapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.R
import fr.jbme.raiderioapp.data.model.character.RIOCharacterResponse
import fr.jbme.raiderioapp.data.model.login.LoggedInUser
import fr.jbme.raiderioapp.data.model.utils.APIError
import fr.jbme.raiderioapp.network.login.LoginRepository
import fr.jbme.raiderioapp.network.utils.NetworkErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(realmName: String, characterName: String, region: String) {
        loginRepository.login(
            realmName,
            characterName,
            region,
            object : Callback<RIOCharacterResponse> {
                override fun onFailure(call: Call<RIOCharacterResponse>, t: Throwable) {
                    _loginResult.value =
                        LoginResult(error = t.message)
                }

                override fun onResponse(
                    call: Call<RIOCharacterResponse>,
                    response: Response<RIOCharacterResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()!!
                        _loginResult.value =
                            LoginResult(
                                success = LoggedInUser(
                                    body.realm!!,
                                    body.name!!,
                                    body.region!!
                                )
                            )
                    } else {
                        val error = NetworkErrorUtils.parseRIOError(response)
                        onFailure(
                            call,
                            APIError(
                                error.message,
                                error.statusCode,
                                error.error
                            )
                        )
                    }

                }
            })
    }

    fun logout() {
        loginRepository.logout()
    }

    fun loginDataChanged(realmName: String, characterName: String) {
        if (!isRealmNameValid(realmName) && realmName != "") {
            _loginForm.value = LoginFormState(realmNameError = R.string.invalid_realm_name)
        } else if (!isCharacterNameValid(characterName) && characterName != "") {
            _loginForm.value = LoginFormState(characterNameError = R.string.invalid_character_name)
        } else if (realmName == "" || characterName == "") {
            _loginForm.value = LoginFormState(isDataValid = false)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder realm name validation check
    private fun isRealmNameValid(realmName: String): Boolean {
        return realmName.replace('-', ' ').matches(Regex("[a-zA-Z ]+"))
    }

    // A placeholder RIOCharacter name validation check
    private fun isCharacterNameValid(characterName: String): Boolean {
        return characterName.matches(Regex("[a-zA-Z]+"))
    }
}
