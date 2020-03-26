package fr.jbme.raiderioapp.service.repository

import fr.jbme.raiderioapp.service.model.blizzard.characterMedia.CharacterMedia
import fr.jbme.raiderioapp.service.model.blizzard.characterProfile.CharacterProfile
import fr.jbme.raiderioapp.service.model.blizzard.profileInfo.ProfileInfo
import fr.jbme.raiderioapp.service.model.login.Result
import fr.jbme.raiderioapp.service.network.retrofit.RetrofitBlizzardInstance
import fr.jbme.raiderioapp.service.network.service.BlizzardService
import fr.jbme.raiderioapp.service.repository.callback.DataCallback
import fr.jbme.raiderioapp.utils.network.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CharacterRepository {
    private val blizzardService =
        RetrofitBlizzardInstance.retrofitInstance?.create(BlizzardService::class.java)

    fun fetchProfileInfo(callback: DataCallback) {
        blizzardService?.getProfileInfo()
            ?.enqueue(object : Callback<ProfileInfo> {
                override fun onFailure(call: Call<ProfileInfo>, t: Throwable) {
                    throw t
                }

                override fun onResponse(
                    call: Call<ProfileInfo>,
                    response: Response<ProfileInfo>
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
        blizzardService?.getCharacterMedia(realm, characterName)
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

    fun fetchCharacterData(realm: String, characterName: String, callback: DataCallback) {
        blizzardService?.getCharacterProfile(realm, characterName)
            ?.enqueue(object : Callback<CharacterProfile> {
                override fun onFailure(call: Call<CharacterProfile>, t: Throwable) {
                    throw t
                }

                override fun onResponse(
                    call: Call<CharacterProfile>,
                    response: Response<CharacterProfile>
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