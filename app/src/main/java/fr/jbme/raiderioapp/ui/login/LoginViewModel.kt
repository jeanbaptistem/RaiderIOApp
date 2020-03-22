package fr.jbme.raiderioapp.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.model.blizzard.login.AccessTokenResponse
import fr.jbme.raiderioapp.model.blizzard.login.TokenCheckResponse
import fr.jbme.raiderioapp.model.login.Result
import fr.jbme.raiderioapp.network.login.BlizzardLoginService
import fr.jbme.raiderioapp.network.login.LoginRepository
import fr.jbme.raiderioapp.network.login.RetrofitBlizzardLoginInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loggedUser = MutableLiveData<AccessTokenResponse>()
    var loggedUser: LiveData<AccessTokenResponse> = _loggedUser

    fun login(code: String?) {
        Log.i("login", code.toString())
        if (code != null) {
            val subject = loginRepository.login(code)
            subject.observeForever {
                _loggedUser.value = it
            }
        } else {
            _loggedUser.value = null
        }
    }

    private val _checkToken = MutableLiveData<Result<TokenCheckResponse>>()
    var tokenCheck: LiveData<Result<TokenCheckResponse>> = _checkToken

    private var blizzardLoginService: BlizzardLoginService? =
        RetrofitBlizzardLoginInstance.retrofitInstance?.create(
            BlizzardLoginService::class.java
        )

    fun checkToken(token: String) {
        blizzardLoginService?.checkTokenValidity(token)
            ?.enqueue(object : Callback<TokenCheckResponse> {
                override fun onFailure(call: Call<TokenCheckResponse>, t: Throwable) {
                    _checkToken.value = Result.Error(Exception(t.message))
                }

                override fun onResponse(
                    call: Call<TokenCheckResponse>,
                    response: Response<TokenCheckResponse>
                ) {
                    if (response.isSuccessful) {
                        _checkToken.value = Result.Success(response.body()!!)
                    } else {
                        _checkToken.value =
                            Result.Error(Exception("Blizzard token check failed, code: ${response.code()}"))
                    }
                }

            })
    }
}
