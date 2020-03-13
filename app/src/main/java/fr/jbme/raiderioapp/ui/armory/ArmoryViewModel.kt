package fr.jbme.raiderioapp.ui.armory

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

class ArmoryViewModel : ViewModel() {

    private val _gearedCharacter = MutableLiveData<CharacterResponse>()
    val gearedCharacter: LiveData<CharacterResponse> = _gearedCharacter

    private var raiderIOService: RaiderIOService? =
        RetrofitInstance.retrofitInstance?.create(
            RaiderIOService::class.java
        )

    fun fetchData(region: String?, realm: String?, name: String?) {
        var call: Call<CharacterResponse>? = null
        try {
            call = raiderIOService?.getGearedCharacter(region!!, realm!!, name!!)
        } catch (e: Exception) {
            // Todo: handle sharedPref error
        }
        call?.enqueue(object : Callback<CharacterResponse> {
            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                //Todo: handle network error
            }

            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful) {
                    _gearedCharacter.value = response.body()!!
                } else {
                    val error = NetworkErrorUtils.parseError(response)
                    onFailure(call, APIException(error.message))
                }
            }

        })
    }
}