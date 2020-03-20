package fr.jbme.raiderioapp.ui.navigation.navHeader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.data.model.character.RIOCharacterResponse
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
                throw t
            }

            override fun onResponse(
                call: Call<RIOCharacterResponse>,
                response: Response<RIOCharacterResponse>
            ) {
                if (response.isSuccessful) {
                    _character.value = response.body()
                } else {
                    val error =
                        NetworkErrorUtils.parseRIOError(response)
                    onFailure(call, error)
                }
            }
        })
    }
}