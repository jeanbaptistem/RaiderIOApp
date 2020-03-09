package fr.jbme.raiderioapp.ui.navHeader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.data.model.character.CharacterResponse
import fr.jbme.raiderioapp.network.RaiderIOService
import fr.jbme.raiderioapp.network.RetrofitInstance
import fr.jbme.raiderioapp.network.utils.APIException
import fr.jbme.raiderioapp.network.utils.NetworkErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NavHeaderViewModel : ViewModel() {
    private val raiderIOService =
        RetrofitInstance.retrofitInstance?.create(RaiderIOService::class.java)

    private val _character = MutableLiveData<CharacterResponse>()
    val character: LiveData<CharacterResponse> = _character

    fun fetchData(region: String, realm: String, name: String) {
        val call = raiderIOService?.getCharacterInfo(region, realm, name)
        call?.enqueue(object : Callback<CharacterResponse> {
            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                //TODO : handle error
            }

            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful) {
                    _character.value = response.body()
                } else {
                    val errorResponse =
                        NetworkErrorUtils.parseError(response)
                    onFailure(call, APIException(errorResponse.statusCode, errorResponse.message))
                }
            }
        })
    }

}