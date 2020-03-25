package fr.jbme.raiderioapp.service.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.jbme.raiderioapp.CLIENT_ID
import fr.jbme.raiderioapp.CLIENT_SECRET
import fr.jbme.raiderioapp.service.model.blizzard.login.AccessTokenResponse
import fr.jbme.raiderioapp.service.network.retrofit.RetrofitBlizzardLoginInstance
import fr.jbme.raiderioapp.service.network.service.BlizzardLoginService
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    private var blizzardLoginService: BlizzardLoginService? =
        RetrofitBlizzardLoginInstance.retrofitInstance?.create(
            BlizzardLoginService::class.java
        )
    private var _accessToken = MutableLiveData<AccessTokenResponse>()
    val accessToken: LiveData<AccessTokenResponse> = _accessToken

    fun login(code: String) {
        val credential: String = Credentials.basic(CLIENT_ID, CLIENT_SECRET)
        blizzardLoginService?.requestAccessToken(credential, code)
            ?.enqueue(object : Callback<AccessTokenResponse> {
                override fun onFailure(call: Call<AccessTokenResponse>, t: Throwable) {
                    //TODO: handle errors
                    Log.i("accessTokenFailure", t.message.toString())
                }

                override fun onResponse(
                    call: Call<AccessTokenResponse>,
                    response: Response<AccessTokenResponse>
                ) {
                    if (response.isSuccessful) {
                        _accessToken.value = response.body()
                    } else {
                        Log.i("accessTokenError", response.code().toString())
                    }
                }
            })
    }

    fun logout() {
        // TODO: revoke authentication
    }
}

