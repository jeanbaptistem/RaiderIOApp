package fr.jbme.raiderioapp.ui.drawer.navHeader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.data.model.character.RIOCharacterResponse
import fr.jbme.raiderioapp.data.model.utils.APIError
import fr.jbme.raiderioapp.network.factory.RetrofitRaiderIOInstance
import fr.jbme.raiderioapp.network.services.RaiderIOService
import fr.jbme.raiderioapp.network.utils.NetworkErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NavHeaderViewModel : ViewModel() {
    private val raiderIOService =
        RetrofitRaiderIOInstance.retrofitInstance?.create(RaiderIOService::class.java)

    private val _character = MutableLiveData<RIOCharacterResponse>()
    val character: LiveData<RIOCharacterResponse> = _character

    fun fetchData(region: String, realm: String, name: String) {
        val call = raiderIOService?.getCharacterInfo(region, realm, name)
        call?.enqueue(object : Callback<RIOCharacterResponse> {
            override fun onFailure(call: Call<RIOCharacterResponse>, t: Throwable) {
                //TODO : handle error
            }

            override fun onResponse(
                call: Call<RIOCharacterResponse>,
                responseRIO: Response<RIOCharacterResponse>
            ) {
                if (responseRIO.isSuccessful) {
                    _character.value = responseRIO.body()
                } else {
                    val errorResponse =
                        NetworkErrorUtils.parseRIOError(responseRIO)
                    onFailure(
                        call,
                        APIError(
                            errorResponse.message,
                            errorResponse.statusCode,
                            errorResponse.error
                        )
                    )
                }
            }
        })
    }

}