package fr.jbme.raiderioapp.ui.navigation.toolbar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.model.BlizzCharacter.BlizCharacterResponse
import fr.jbme.raiderioapp.network.factory.RetrofitBlizzardWebInstance
import fr.jbme.raiderioapp.network.services.BlizzardWebService
import fr.jbme.raiderioapp.network.utils.NetworkErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ToolbarViewModel : ViewModel() {
    private val blizzardWebService =
        RetrofitBlizzardWebInstance.retrofitInstance?.create(BlizzardWebService::class.java)

    private val _character = MutableLiveData<BlizCharacterResponse>()
    val character: LiveData<BlizCharacterResponse> = _character

    fun fetchData(region: String, realm: String, name: String) {
        val call = blizzardWebService?.getCharacterInfo(region, realm, name)
        call?.enqueue(object : Callback<BlizCharacterResponse> {
            override fun onFailure(call: Call<BlizCharacterResponse>, t: Throwable) {
                throw t
            }

            override fun onResponse(
                call: Call<BlizCharacterResponse>,
                response: Response<BlizCharacterResponse>
            ) {
                if (response.isSuccessful) {
                    _character.value = response.body()
                } else {
                    val error = NetworkErrorUtils.parseRIOError(response)
                    throw  error
                }
            }
        })
    }
}