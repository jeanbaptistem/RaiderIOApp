package fr.jbme.raiderioapp.service.repository

import fr.jbme.raiderioapp.REGION
import fr.jbme.raiderioapp.service.model.blizzard.DungeonInfo
import fr.jbme.raiderioapp.service.model.raiderio.CharacterBestRuns
import fr.jbme.raiderioapp.service.model.raiderio.CharacterRanks
import fr.jbme.raiderioapp.service.model.raiderio.CharacterScore
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

object DungeonRepository {
    private val blizzardService: BlizzardService? =
        RetrofitBlizzardInstance.retrofitInstance?.create(
            BlizzardService::class.java
        )

    private val raiderIOService: RaiderIOService? =
        RetrofitRaiderIOInstance.retrofitInstance?.create(
            RaiderIOService::class.java
        )

    fun fetchRanks(realmSlug: String, characterName: String, callback: DataCallback) {
        raiderIOService?.getCharacterRanksRio(REGION, realmSlug, characterName)
            ?.enqueue(object :
                Callback<CharacterRanks> {
                override fun onFailure(call: Call<CharacterRanks>, t: Throwable) {
                    throw  t
                }

                override fun onResponse(
                    call: Call<CharacterRanks>,
                    response: Response<CharacterRanks>
                ) {
                    if (response.isSuccessful) {
                        callback.onDataLoaded(Result.Success(response.body()!!))
                    } else {
                        val error = NetworkUtils.parseRIOError(response)
                        callback.onDataNotAvailable(Result.Error(error))
                    }
                }
            })
    }

    fun fetchScore(realmSlug: String, characterName: String, callback: DataCallback) {
        raiderIOService?.getCharacterScoreRio(REGION, realmSlug, characterName)
            ?.enqueue(object : Callback<CharacterScore> {
                override fun onFailure(call: Call<CharacterScore>, t: Throwable) {
                    throw t
                }

                override fun onResponse(
                    call: Call<CharacterScore>,
                    response: Response<CharacterScore>
                ) {
                    if (response.isSuccessful) {
                        callback.onDataLoaded(Result.Success(response.body()!!))
                    } else {
                        val error = NetworkUtils.parseRIOError(response)
                        callback.onDataNotAvailable(Result.Error(error))
                    }
                }

            })
    }

    fun fetchRioBestRuns(realmSlug: String, characterName: String, callback: DataCallback) {
        raiderIOService?.getCharacterBestRunsRio(REGION, realmSlug, characterName)
            ?.enqueue(object : Callback<CharacterBestRuns> {
                override fun onFailure(call: Call<CharacterBestRuns>, t: Throwable) {
                    throw t
                }

                override fun onResponse(
                    call: Call<CharacterBestRuns>,
                    response: Response<CharacterBestRuns>
                ) {
                    if (response.isSuccessful) {
                        callback.onDataLoaded(Result.Success(response.body()?.mythicPlusBestRuns!!))
                    } else {
                        val error = NetworkUtils.parseRIOError(response)
                        callback.onDataNotAvailable(Result.Error(error))
                    }
                }

            })
    }

    fun fetchBlizzardDungeons(realm: String, name: String, callback: DataCallback) {
        blizzardService?.getCharacterDungeonBliz(realm, name)
            ?.enqueue(object : Callback<DungeonInfo> {
                override fun onFailure(call: Call<DungeonInfo>, t: Throwable) {
                    throw t
                }

                override fun onResponse(call: Call<DungeonInfo>, response: Response<DungeonInfo>) {
                    if (response.isSuccessful) {
                        callback.onDataLoaded(Result.Success(response.body()!!))
                    } else {
                        val error = NetworkUtils.parseBlizError(response)
                        callback.onDataNotAvailable(Result.Error(error))
                    }
                }
            })
    }
}