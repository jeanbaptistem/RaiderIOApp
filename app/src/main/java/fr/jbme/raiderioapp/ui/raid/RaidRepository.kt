package fr.jbme.raiderioapp.ui.raid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.jbme.raiderioapp.model.blizzard.raidInfo.Instances
import fr.jbme.raiderioapp.model.blizzard.raidInfo.RaidInfo
import fr.jbme.raiderioapp.network.main.BlizzardService
import fr.jbme.raiderioapp.network.main.RetrofitBlizzardInstance
import fr.jbme.raiderioapp.network.utils.NetworkErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RaidRepository {
    private val globalParamProfile = mapOf("namespace" to "profile-eu", "locale" to "fr_FR")

    private var blizzardService: BlizzardService? =
        RetrofitBlizzardInstance.retrofitInstance?.create(
            BlizzardService::class.java
        )

    fun fetchRaidInfo(realmSlug: String?, characterName: String?): LiveData<List<Instances>> {
        val raidInfo = MutableLiveData<List<Instances>>()
        blizzardService?.getCharacterRaidInfo(realmSlug, characterName, globalParamProfile)
            ?.enqueue(object :
                Callback<RaidInfo> {
                override fun onFailure(call: Call<RaidInfo>, t: Throwable) {
                    throw  t
                }

                override fun onResponse(
                    call: Call<RaidInfo>,
                    response: Response<RaidInfo>
                ) {
                    if (response.isSuccessful) {
                        raidInfo.value =
                            response.body()?.expansions?.first { expac -> expac.expansion.id == 396 }?.instances
                    } else {
                        val error = NetworkErrorUtils.parseBlizError(response)
                        throw error
                    }
                }
            })
        return raidInfo
    }
}