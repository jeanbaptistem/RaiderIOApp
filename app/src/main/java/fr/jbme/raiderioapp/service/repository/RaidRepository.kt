package fr.jbme.raiderioapp.service.repository

import fr.jbme.raiderioapp.REGION
import fr.jbme.raiderioapp.service.model.blizzard.raidInfo.RaidInfo
import fr.jbme.raiderioapp.service.model.login.Result
import fr.jbme.raiderioapp.service.model.raiderio.RaidInfoRio
import fr.jbme.raiderioapp.service.network.retrofit.RetrofitBlizzardInstance
import fr.jbme.raiderioapp.service.network.retrofit.RetrofitRaiderIOInstance
import fr.jbme.raiderioapp.service.network.service.BlizzardService
import fr.jbme.raiderioapp.service.network.service.RaiderIOService
import fr.jbme.raiderioapp.utils.network.NetworkUtils
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

    fun fetchRaidInfo(realmSlug: String, characterName: String, callback: DataCallback) {
        blizzardService?.getCharacterRaidInfo(
            realmSlug, characterName, globalParamProfile
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
                        callback.onDataLoaded(
                            Result.Success(response.body()?.expansions
                                ?.first { expac -> expac.expansion.id == 396 }
                                ?.instances!!))
                    } else {
                        val error = NetworkUtils.parseBlizError(response)
                        callback.onDataNotAvailable(Result.Error(error))
                    }
                }
            })
    }

    fun fetchRaidInfoRio(realmSlug: String, characterName: String, callback: DataCallback) {
        raiderIOService?.getRaidCharacter(REGION, realmSlug, characterName)
            ?.enqueue(object : Callback<RaidInfoRio> {
                override fun onFailure(call: Call<RaidInfoRio>, t: Throwable) {
                    throw t
                }

                override fun onResponse(call: Call<RaidInfoRio>, response: Response<RaidInfoRio>) {
                    if (response.isSuccessful) {
                        callback.onDataLoaded(Result.Success(response.body()?.raid_progression!!))
                    } else {
                        val error = NetworkUtils.parseRIOError(response)
                        callback.onDataNotAvailable(Result.Error(error))
                    }
                }
            })
    }
}