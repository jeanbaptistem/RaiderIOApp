package fr.jbme.raiderioapp.service.repository

import fr.jbme.raiderioapp.REGION
import fr.jbme.raiderioapp.service.model.blizzard.dungeonInfo.DungeonInfo
import fr.jbme.raiderioapp.service.model.login.Result
import fr.jbme.raiderioapp.service.model.raiderio.characterScore.CharacterScore
import fr.jbme.raiderioapp.service.model.raiderio.dungeonRanks.DungeonsRanks
import fr.jbme.raiderioapp.service.model.raiderio.dungeonsBestRuns.BestRuns
import fr.jbme.raiderioapp.service.network.retrofit.RetrofitBlizzardInstance
import fr.jbme.raiderioapp.service.network.retrofit.RetrofitRaiderIOInstance
import fr.jbme.raiderioapp.service.network.service.BlizzardService
import fr.jbme.raiderioapp.service.network.service.RaiderIOService
import fr.jbme.raiderioapp.service.repository.callback.DataCallback
import fr.jbme.raiderioapp.utils.network.NetworkUtils
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
        raiderIOService?.getCharacterRanks(REGION, realmSlug, characterName)
            ?.enqueue(object :
                Callback<DungeonsRanks> {
                override fun onFailure(call: Call<DungeonsRanks>, t: Throwable) {
                    throw  t
                }

                override fun onResponse(
                    call: Call<DungeonsRanks>,
                    response: Response<DungeonsRanks>
                ) {
                    if (response.isSuccessful) {
                        callback.onDataLoaded(Result.Success(response.body()?.mythic_plus_ranks!!))
                    } else {
                        val error = NetworkUtils.parseRIOError(response)
                        callback.onDataNotAvailable(Result.Error(error))
                    }
                }
            })
    }

    fun fetchScore(realmSlug: String, characterName: String, callback: DataCallback) {
        raiderIOService?.getCharacterScore(REGION, realmSlug, characterName)
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
        raiderIOService?.getCharacterBestRuns(REGION, realmSlug, characterName)
            ?.enqueue(object : Callback<BestRuns> {
                override fun onFailure(call: Call<BestRuns>, t: Throwable) {
                    throw t
                }

                override fun onResponse(call: Call<BestRuns>, response: Response<BestRuns>) {
                    if (response.isSuccessful) {
                        callback.onDataLoaded(Result.Success(response.body()?.mythic_plus_best_runs!!))
                    } else {
                        val error = NetworkUtils.parseRIOError(response)
                        callback.onDataNotAvailable(Result.Error(error))
                    }
                }

            })
    }

    fun fetchBlizzardDungeons(realm: String, name: String, callback: DataCallback) {
        blizzardService?.getCharacterDungeon(realm, name)?.enqueue(object : Callback<DungeonInfo> {
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