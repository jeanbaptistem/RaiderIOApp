package fr.jbme.raiderioapp.ui.raid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.data.model.character.CharacterResponse
import fr.jbme.raiderioapp.data.model.character.Raid
import fr.jbme.raiderioapp.data.model.character.RaidProgression
import fr.jbme.raiderioapp.network.RetrofitRaiderIOInstance
import fr.jbme.raiderioapp.network.services.RaiderIOService
import fr.jbme.raiderioapp.network.utils.APIError
import fr.jbme.raiderioapp.network.utils.NetworkErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RaidViewModel : ViewModel() {

    private val _raid = MutableLiveData<List<Raid>>()
    val raid: LiveData<List<Raid>> = _raid

    private var raiderIOService: RaiderIOService? =
        RetrofitRaiderIOInstance.retrofitInstance?.create(
            RaiderIOService::class.java
        )

    fun fetchRaidData(region: String, realm: String, name: String) {
        try {
            raiderIOService
                ?.getRaidCharacter(region, realm, name)
                ?.enqueue(object :
                    Callback<CharacterResponse> {
                    override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                        throw  APIError(t.message)
                    }

                    override fun onResponse(
                        call: Call<CharacterResponse>,
                        response: Response<CharacterResponse>
                    ) {
                        if (response.isSuccessful) {
                            _raid.value =
                                raidProgressionToRaidList(response.body()!!.raidProgression)
                        } else {
                            val error = NetworkErrorUtils.parseRIOError(response)
                            onFailure(call, APIError(error.message, error.statusCode, error.error))
                        }
                    }
                })
        } catch (e: Exception) {
            throw APIError(e.message)
        }
    }

    private fun raidProgressionToRaidList(raidProgression: RaidProgression?): List<Raid> {
        return listOfNotNull(
            raidProgression?.nyalothaTheWakingCity?.apply { name = "Nyalotha The Waking City" },
            raidProgression?.theEternalPalace?.apply { name = "The Eternal Palace" },
            raidProgression?.crucibleOfStorms?.apply { name = "Crucible Of Storms" },
            raidProgression?.battleOfDazaralor?.apply { name = "Battle Of Dazaralor" },
            raidProgression?.uldir?.apply { name = "Uldir" }
        )
    }
}