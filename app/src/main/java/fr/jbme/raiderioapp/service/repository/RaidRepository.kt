package fr.jbme.raiderioapp.service.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fr.jbme.raiderioapp.REGION
import fr.jbme.raiderioapp.service.model.blizzard.raidInfo.Instances
import fr.jbme.raiderioapp.service.model.blizzard.raidInfo.RaidInfo
import fr.jbme.raiderioapp.service.model.raiderio.RaidInfoRio
import fr.jbme.raiderioapp.service.model.raiderio.RaidProgression
import fr.jbme.raiderioapp.service.network.retrofit.RetrofitBlizzardInstance
import fr.jbme.raiderioapp.service.network.retrofit.RetrofitRaiderIOInstance
import fr.jbme.raiderioapp.utils.NetworkErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RaidRepository {
    private val globalParamProfile = mapOf("namespace" to "profile-eu", "locale" to "fr_FR")

    private val blizzardService: BlizzardService? =
        RetrofitBlizzardInstance.retrofitInstance?.create(
            BlizzardService::class.java
        )

    private val raiderIOService: RaiderIOService? =
        RetrofitRaiderIOInstance.retrofitInstance?.create(
            RaiderIOService::class.java
        )

    fun fetchRaidInfo(realmSlug: String?, characterName: String?): LiveData<List<Instances>> {
        val raidInfo = MutableLiveData<List<Instances>>()
        blizzardService?.getCharacterRaidInfo(
                realmSlug, characterName,
                globalParamProfile
            )
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

    fun fetchRaidInfoRio(realmSlug: String?, characterName: String?): LiveData<RaidProgression> {
        val raidInfoRio = MutableLiveData<RaidProgression>()
        raiderIOService?.getRaidCharacter(REGION, realmSlug, characterName)
            ?.enqueue(object : Callback<RaidInfoRio> {
                override fun onFailure(call: Call<RaidInfoRio>, t: Throwable) {
                    throw t
                }

                override fun onResponse(call: Call<RaidInfoRio>, response: Response<RaidInfoRio>) {
                    if (response.isSuccessful) {
                        raidInfoRio.value = response.body()?.raid_progression
                    } else {
                        val error = NetworkErrorUtils.parseRIOError(response)
                        throw error
                    }
                }
            })
        return raidInfoRio
    }
}