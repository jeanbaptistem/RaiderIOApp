package fr.jbme.raiderioapp.view.activity.main

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.RaiderIOApp
import fr.jbme.raiderioapp.SELECTED_CHARACTER
import fr.jbme.raiderioapp.SHARED_PREF_KEY
import fr.jbme.raiderioapp.service.model.blizzard.characterMedia.CharacterMedia
import fr.jbme.raiderioapp.service.model.blizzard.characterProfile.CharacterProfile
import fr.jbme.raiderioapp.service.model.blizzard.profileInfo.ProfileInfo
import fr.jbme.raiderioapp.service.model.login.Result
import fr.jbme.raiderioapp.service.repository.CharacterRepository
import fr.jbme.raiderioapp.service.repository.callback.DataCallback
import fr.jbme.raiderioapp.utils.Whatever
import fr.jbme.raiderioapp.view.activity.main.popupWindow.PopupCharacterItem


class MainActivityViewModel : ViewModel() {
    private var sharedPref: SharedPreferences? =
        RaiderIOApp.context?.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
    private val _profileInfoData = MutableLiveData<ProfileInfo>()
    val profileInfoData: LiveData<ProfileInfo>
        get() = _profileInfoData

    private val _profileInfoLoading = MutableLiveData<Boolean>()
    val profileInfoLoading: LiveData<Boolean>
        get() = _profileInfoLoading

    private val viewSelectedCharacter = MutableLiveData<PopupCharacterItem>()
    private val modelSelectedCharacter = Transformations.distinctUntilChanged(viewSelectedCharacter)

    val getSelectedCharacter: LiveData<PopupCharacterItem>
        get() = modelSelectedCharacter

    init {
        _profileInfoLoading.value = true
        CharacterRepository.fetchProfileInfo(object : DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                _profileInfoData.value = result.data as ProfileInfo
                _profileInfoLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                Log.i("Profile Data error", error.exception.message.toString())
                _profileInfoLoading.value = false
            }
        })

        sharedPref?.let { sharedPref ->
            sharedPref.getString(SELECTED_CHARACTER, null)?.let {
                selectedCharacter(
                    PopupCharacterItem(
                        it
                    )
                )
            }
        }
    }

    fun selectedCharacter(selectedCharName: PopupCharacterItem?) {
        viewSelectedCharacter.postValue(selectedCharName)
    }

    val updateSharedPref: LiveData<Boolean> =
        Transformations.switchMap(modelSelectedCharacter) { character ->
            Log.i("SharedPrefEdit", "true")
            with(sharedPref?.edit()) {
                this?.putString(SELECTED_CHARACTER, character.name + "-" + character.realmSlug)
                this?.apply()
            }
            MutableLiveData(true)
        }

    val characterData: LiveData<CharacterProfile> =
        Transformations.switchMap(modelSelectedCharacter) { character ->
            val name = Whatever.parseToSlug(character.name)!!
            val realm = character.realmSlug
            loadCharacterData(realm, name)
        }

    val characterMedia: LiveData<CharacterMedia> =
        Transformations.switchMap(modelSelectedCharacter) { character ->
            val name = Whatever.parseToSlug(character.name)!!
            val realm = character.realmSlug
            loadCharacterMedia(realm, name)
        }

    private val _characterDataLoading = MutableLiveData<Boolean>()
    val characterDataLoading: LiveData<Boolean>
        get() = _characterDataLoading

    private fun loadCharacterData(realm: String, name: String): LiveData<CharacterProfile> {
        val characterProfileResult = MutableLiveData<CharacterProfile>()
        _characterDataLoading.value = true
        CharacterRepository.fetchCharacterData(realm, name, object : DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                characterProfileResult.value = result.data as CharacterProfile
                _characterDataLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                Log.i("Character data error", error.exception.message.toString())
                _characterDataLoading.value = false
            }
        })

        return characterProfileResult
    }

    private val _characterMediaLoading = MutableLiveData<Boolean>()
    val characterMediaLoading: LiveData<Boolean>
        get() = _characterMediaLoading

    private fun loadCharacterMedia(realm: String, name: String): LiveData<CharacterMedia> {
        val characterMediaResult = MutableLiveData<CharacterMedia>()
        _characterMediaLoading.value = true
        CharacterRepository.fetchCharacterMedia(realm, name, object : DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                characterMediaResult.value = result.data as CharacterMedia
                _characterMediaLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                Log.i("Character media error", error.exception.message.toString())
                _characterMediaLoading.value = false
            }
        })
        return characterMediaResult
    }
}