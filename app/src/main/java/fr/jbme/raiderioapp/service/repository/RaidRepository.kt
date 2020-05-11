package fr.jbme.raiderioapp.service.repository

import fr.jbme.raiderioapp.REGION
import fr.jbme.raiderioapp.service.model.blizzard.RaidInfo
import fr.jbme.raiderioapp.service.model.raiderio.CharacterRaid
import fr.jbme.raiderioapp.service.network.retrofit.RetrofitBlizzardInstance
import fr.jbme.raiderioapp.service.network.retrofit.RetrofitRaiderIOInstance
import fr.jbme.raiderioapp.service.network.service.BlizzardService
import fr.jbme.raiderioapp.service.network.service.RaiderIOService
import fr.jbme.raiderioapp.service.repository.callback.DataCallback
import fr.jbme.raiderioapp.utils.network.NetworkUtils
import fr.jbme.raiderioapp.utils.network.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RaidRepository {
    private val blizzardService: BlizzardService? =
        RetrofitBlizzardInstance.retrofitInstance?.create(
            BlizzardService::class.java
        )

    private val raiderIOService: RaiderIOService? =
        RetrofitRaiderIOInstance.retrofitInstance?.create(
            RaiderIOService::class.java
        )

    fun fetchRaidInfo(realmSlug: String, characterName: String, callback: DataCallback) {
        blizzardService?.getCharacterRaidInfoBliz(realmSlug, characterName)
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
                        val res = mutableListOf<RaidInfo.Expansion.Instance>()
                        response.body()?.expansions?.forEach { expac -> res.addAll(expac?.instances as List<RaidInfo.Expansion.Instance>) }
                        callback.onDataLoaded(Result.Success(res.asReversed().toList()))
                    } else {
                        val error = NetworkUtils.parseBlizError(response)
                        callback.onDataNotAvailable(Result.Error(error))
                    }
                }
            })
    }

    fun fetchRaidInfoRio(realmSlug: String, characterName: String, callback: DataCallback) {
        raiderIOService?.getRaidCharacterRio(REGION, realmSlug, characterName)
            ?.enqueue(object : Callback<CharacterRaid> {
                override fun onFailure(call: Call<CharacterRaid>, t: Throwable) {
                    throw t
                }

                override fun onResponse(
                    call: Call<CharacterRaid>,
                    response: Response<CharacterRaid>
                ) {
                    if (response.isSuccessful) {
                        callback.onDataLoaded(Result.Success(response.body()?.raidProgression!!))
                    } else {
                        val error = NetworkUtils.parseRIOError(response)
                        callback.onDataNotAvailable(Result.Error(error))
                    }
                }
            })
    }
}