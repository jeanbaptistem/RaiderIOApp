package fr.jbme.raiderioapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.model.blizzard.characterMedia.CharacterMedia
import fr.jbme.raiderioapp.model.blizzard.characterProfile.CharacterProfile
import fr.jbme.raiderioapp.model.blizzard.profileInfo.ProfileInfo
import fr.jbme.raiderioapp.network.main.BlizzardService
import fr.jbme.raiderioapp.network.main.RetrofitBlizzardInstance
import fr.jbme.raiderioapp.network.utils.NetworkErrorUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivityViewModel : ViewModel() {
    private val blizzardService =
        RetrofitBlizzardInstance.retrofitInstance?.create(BlizzardService::class.java)

    private val globalParamProfile = mapOf("namespace" to "profile-eu", "locale" to "fr_FR")

    private val _profileInfo = MutableLiveData<ProfileInfo>()
    val profileInfo: LiveData<ProfileInfo> = _profileInfo

    fun fetchProfileInfo() {
        blizzardService?.getProfileInfo(globalParamProfile)
            ?.enqueue(object : Callback<ProfileInfo> {
                override fun onFailure(call: Call<ProfileInfo>, t: Throwable) {
                    throw t
                }

                override fun onResponse(
                    call: Call<ProfileInfo>,
                    response: Response<ProfileInfo>
                ) {
                    if (response.isSuccessful) {
                        _profileInfo.value = response.body()
                    } else {
                        val error = NetworkErrorUtils.parseBlizError(response)
                        throw  error
                    }
                }
            })
    }

    private val _characterMedia = MutableLiveData<CharacterMedia>()
    val characterMedia: LiveData<CharacterMedia> = _characterMedia

    fun fetchCharacterMedia(realm: String, characterName: String) {
        blizzardService?.getCharacterMedia(realm, characterName, globalParamProfile)
            ?.enqueue(object : Callback<CharacterMedia> {
                override fun onFailure(call: Call<CharacterMedia>, t: Throwable) {
                    throw t
                }

                override fun onResponse(
                    call: Call<CharacterMedia>,
                    response: Response<CharacterMedia>
                ) {
                    if (response.isSuccessful) {
                        _characterMedia.value = response.body()
                    } else {
                        val error = NetworkErrorUtils.parseBlizError(response)
                        throw  error
                    }
                }

            })
    }

    private val _characterProfile = MutableLiveData<CharacterProfile>()
    val characterProfile: LiveData<CharacterProfile> = _characterProfile

    fun fetchCharacterProfile(realm: String, characterName: String) {
        blizzardService?.getCharacterProfile(realm, characterName, globalParamProfile)
            ?.enqueue(object : Callback<CharacterProfile> {
                override fun onFailure(call: Call<CharacterProfile>, t: Throwable) {
                    throw t
                }

                override fun onResponse(
                    call: Call<CharacterProfile>,
                    response: Response<CharacterProfile>
                ) {
                    if (response.isSuccessful) {
                        _characterProfile.value = response.body()
                    } else {
                        val error = NetworkErrorUtils.parseBlizError(response)
                        throw  error
                    }
                }
            })
    }

}