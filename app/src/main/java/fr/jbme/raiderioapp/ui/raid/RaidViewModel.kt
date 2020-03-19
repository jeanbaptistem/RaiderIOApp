package fr.jbme.raiderioapp.ui.raid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.data.model.utils.APIError
import fr.jbme.raiderioapp.data.model.wow.character.BlizCharacterResponse
import fr.jbme.raiderioapp.data.model.wow.character.Raids
import fr.jbme.raiderioapp.network.factory.RetrofitBlizzardWebInstance
import fr.jbme.raiderioapp.network.services.BlizzardWebService
import fr.jbme.raiderioapp.network.utils.NetworkErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RaidViewModel : ViewModel() {

    private var _raid = MutableLiveData<List<Raids>>()
    val raid: LiveData<List<Raids>> = _raid


    private var blizzardWebService: BlizzardWebService? =
        RetrofitBlizzardWebInstance.retrofitInstance?.create(
            BlizzardWebService::class.java
        )

    fun fetchRaidData(region: String?, realm: String?, name: String?) {
        try {
            blizzardWebService
                ?.getCharacterInfo(region!!, realm!!, name!!)
                ?.enqueue(object :
                    Callback<BlizCharacterResponse> {
                    override fun onFailure(call: Call<BlizCharacterResponse>, t: Throwable) {
                        throw  APIError(t.message)
                    }

                    override fun onResponse(
                        call: Call<BlizCharacterResponse>,
                        response: Response<BlizCharacterResponse>
                    ) {
                        if (response.isSuccessful) {
                            _raid.value = response.body()?.character?.pve?.raids
                        } else {
                            val error = NetworkErrorUtils.parseRIOError(response)
                            onFailure(call, error)
                        }
                    }
                })
        } catch (e: Exception) {
            throw APIError(e.message)
        }
    }

}