package fr.jbme.raiderioapp.ui.raid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.data.BLIZZARD_ACCESS_TOKEN
import fr.jbme.raiderioapp.data.model.character.CharacterResponse
import fr.jbme.raiderioapp.data.model.character.Raid
import fr.jbme.raiderioapp.data.model.character.RaidProgression
import fr.jbme.raiderioapp.data.model.raidInfo.Instances
import fr.jbme.raiderioapp.data.model.raidInfo.RaidInfoResponse
import fr.jbme.raiderioapp.data.model.utils.APIError
import fr.jbme.raiderioapp.network.factory.RetrofitBlizzardInstance
import fr.jbme.raiderioapp.network.factory.RetrofitRaiderIOInstance
import fr.jbme.raiderioapp.network.services.BlizzardService
import fr.jbme.raiderioapp.network.services.RaiderIOService
import fr.jbme.raiderioapp.network.utils.NetworkErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RaidViewModel : ViewModel() {

    private var _raid = MutableLiveData<List<Raid>>()
    val raid: LiveData<List<Raid>> = _raid

    private var _raidInstances = MutableLiveData<List<Instances>>()
    val raidInstances: LiveData<List<Instances>> = _raidInstances

    private var raiderIOService: RaiderIOService? =
        RetrofitRaiderIOInstance.retrofitInstance?.create(
            RaiderIOService::class.java
        )

    private var blizzardService: BlizzardService? =
        RetrofitBlizzardInstance.retrofitInstance?.create(
            BlizzardService::class.java
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
                            onFailure(
                                call,
                                APIError(
                                    error.message,
                                    error.statusCode,
                                    error.error
                                )
                            )
                        }
                    }
                })
        } catch (e: Exception) {
            throw APIError(e.message)
        }
    }

    private fun raidProgressionToRaidList(raidProgression: RaidProgression?): List<Raid> {
        return listOfNotNull(
            raidProgression?.nyalothaTheWakingCity?.apply { name = "Ny'alotha, the Waking City" },
            raidProgression?.theEternalPalace?.apply { name = "The Eternal Palace" },
            raidProgression?.crucibleOfStorms?.apply { name = "Crucible of Storms" },
            raidProgression?.battleOfDazaralor?.apply { name = "Battle of Dazar'alor" },
            raidProgression?.uldir?.apply { name = "Uldir" }
        )
    }

    fun fetchRaidInstanceData(realmSlug: String, characterName: String) {
        try {
            blizzardService?.getRaidInfo(realmSlug, characterName, BLIZZARD_ACCESS_TOKEN)
                ?.enqueue(object : Callback<RaidInfoResponse> {
                    override fun onFailure(call: Call<RaidInfoResponse>, t: Throwable) {
                        throw APIError(t.message)
                    }

                    override fun onResponse(
                        call: Call<RaidInfoResponse>,
                        response: Response<RaidInfoResponse>
                    ) {
                        if (response.isSuccessful) {
                            _raidInstances.value =
                                response.body()!!.expansions.first { expac -> expac.expansion.name == "Battle for Azeroth" }.instances
                        } else {
                            val error = NetworkErrorUtils.parseBlizError(response)
                            onFailure(
                                call,
                                APIError(
                                    error.message,
                                    error.statusCode,
                                    error.error
                                )
                            )

                        }
                    }

                })
        } catch (e: Exception) {
            throw APIError(e.message)
        }
    }
}