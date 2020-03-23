package fr.jbme.raiderioapp.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.CLIENT_ID
import fr.jbme.raiderioapp.CLIENT_SECRET
import fr.jbme.raiderioapp.service.model.blizzard.login.AccessTokenResponse
import fr.jbme.raiderioapp.service.model.blizzard.login.TokenCheckResponse
import fr.jbme.raiderioapp.service.model.login.Result
import fr.jbme.raiderioapp.service.network.retrofit.RetrofitBlizzardLoginInstance
import fr.jbme.raiderioapp.service.repository.login.BlizzardLoginService
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel : ViewModel() {
    private var blizzardLoginService: BlizzardLoginService? =
        RetrofitBlizzardLoginInstance.retrofitInstance?.create(
            BlizzardLoginService::class.java
        )

    fun checkToken(token: String): LiveData<Result<TokenCheckResponse>> {
        val getValidToken: MutableLiveData<Result<TokenCheckResponse>> by lazy {
            MutableLiveData<Result<TokenCheckResponse>>()
        }
        blizzardLoginService?.checkTokenValidity(token)
            ?.enqueue(object : Callback<TokenCheckResponse> {
                override fun onFailure(call: Call<TokenCheckResponse>, t: Throwable) {
                    getValidToken.value = Result.Error(Exception(t.message))
                }

                override fun onResponse(
                    call: Call<TokenCheckResponse>,
                    response: Response<TokenCheckResponse>
                ) {
                    if (response.isSuccessful) {
                        getValidToken.value = Result.Success(response.body()!!)
                    } else {
                        getValidToken.value =
                            Result.Error(Exception("Blizzard token check failed, code: ${response.code()}"))
                    }
                }

            })
        return getValidToken
    }

    fun loginWithCode(code: String): LiveData<Result<AccessTokenResponse>> {
        val getLoginWithCode: MutableLiveData<Result<AccessTokenResponse>> by lazy {
            MutableLiveData<Result<AccessTokenResponse>>()
        }
        blizzardLoginService?.requestAccessToken(Credentials.basic(CLIENT_ID, CLIENT_SECRET), code)
            ?.enqueue(object : Callback<AccessTokenResponse> {
                override fun onFailure(call: Call<AccessTokenResponse>, t: Throwable) {
                    getLoginWithCode.value = Result.Error(Exception(t.message))
                }

                override fun onResponse(
                    call: Call<AccessTokenResponse>,
                    response: Response<AccessTokenResponse>
                ) {
                    if (response.isSuccessful) {
                        getLoginWithCode.value = Result.Success(response.body()!!)
                    } else {
                        getLoginWithCode.value =
                            Result.Error(Exception("Token request failed with code ${response.code()}"))
                    }
                }

            })
        return getLoginWithCode
    }
}
