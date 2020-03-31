package fr.jbme.raiderioapp.service.repository

import fr.jbme.raiderioapp.service.model.blizzard.AccountProfile
import fr.jbme.raiderioapp.service.model.blizzard.CharacterMedia
import fr.jbme.raiderioapp.service.network.retrofit.RetrofitBlizzardInstance
import fr.jbme.raiderioapp.service.network.service.BlizzardService
import fr.jbme.raiderioapp.service.repository.callback.DataCallback
import fr.jbme.raiderioapp.utils.network.NetworkUtils
import fr.jbme.raiderioapp.utils.network.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainRepository {
    private val blizzardService =
        RetrofitBlizzardInstance.retrofitInstance?.create(BlizzardService::class.java)

    fun fetchProfileInfo(callback: DataCallback) {
        blizzardService?.getProfileInfoBliz()
            ?.enqueue(object : Callback<AccountProfile> {
                override fun onFailure(call: Call<AccountProfile>, t: Throwable) {
                    throw t
                }

                override fun onResponse(
                    call: Call<AccountProfile>,
                    response: Response<AccountProfile>
                ) {
                    if (response.isSuccessful) {
                        callback.onDataLoaded(Result.Success(response.body()!!))
                    } else {
                        val error = NetworkUtils.parseBlizError(response)
                        callback.onDataNotAvailable(Result.Error(error))
                    }
                }
            })
    }

    fun fetchCharacterMedia(realm: String, characterName: String, callback: DataCallback) {
        blizzardService?.getCharacterMediaBliz(realm, characterName)
            ?.enqueue(object : Callback<CharacterMedia> {
                override fun onFailure(call: Call<CharacterMedia>, t: Throwable) {
                    throw t
                }

                override fun onResponse(
                    call: Call<CharacterMedia>,
                    response: Response<CharacterMedia>
                ) {
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