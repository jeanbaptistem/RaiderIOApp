package fr.jbme.raiderioapp.view.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.service.model.blizzard.characterMedia.CharacterMedia
import fr.jbme.raiderioapp.service.model.blizzard.characterProfile.CharacterProfile
import fr.jbme.raiderioapp.service.model.blizzard.profileInfo.ProfileInfo
import fr.jbme.raiderioapp.service.model.login.Result
import fr.jbme.raiderioapp.service.repository.DataCallback
import fr.jbme.raiderioapp.service.repository.MainActivityRepository
import fr.jbme.raiderioapp.utils.Whatever


class MainActivityViewModel : ViewModel() {
    private val _profileInfoData = MutableLiveData<ProfileInfo>()
    val profileInfoData: LiveData<ProfileInfo>
        get() = _profileInfoData

    private val _profileInfoLoading = MutableLiveData<Boolean>()
    val profileInfoLoading: LiveData<Boolean>
        get() = _profileInfoLoading

    private val viewSelectedCharacter = MutableLiveData<String>()
    private val modelSelectedCharacter = Transformations.distinctUntilChanged(viewSelectedCharacter)
    val getSelectedCharacter: LiveData<String>
        get() = modelSelectedCharacter

    init {
        _profileInfoLoading.value = true
        MainActivityRepository.fetchProfileInfo(object : DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                _profileInfoData.value = result.data as ProfileInfo
                _profileInfoLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                Log.i("Profile Data error", error.exception.message.toString())
                _profileInfoLoading.value = false
            }
        })
    }

    fun selectedCharacter(selectedCharName: String?) {
        viewSelectedCharacter.postValue(selectedCharName)
    }

    val characterData: LiveData<CharacterProfile> =
        Transformations.switchMap(modelSelectedCharacter) { character ->
            val name = Whatever.parseToSlug(character.split('-')[0])!!
            val realm = Whatever.parseToSlug(character.split('-')[1])!!
            Log.i("CharacterData", "SwitchMap")
            loadCharacterData(realm, name)
        }

    val characterMedia: LiveData<CharacterMedia> =
        Transformations.switchMap(modelSelectedCharacter) { character ->
            val name = Whatever.parseToSlug(character.split('-')[0])!!
            val realm = Whatever.parseToSlug(character.split('-')[1])!!
            Log.i("CharacterMedia", "SwitchMap")
            loadCharacterMedia(realm, name)
        }

    private val _characterDataLoading = MutableLiveData<Boolean>()
    val characterDataLoading: LiveData<Boolean>
        get() = _characterDataLoading

    private fun loadCharacterData(realm: String, name: String): LiveData<CharacterProfile> {
        val characterProfileResult = MutableLiveData<CharacterProfile>()
        _characterDataLoading.value = true
        MainActivityRepository.fetchCharacterData(realm, name, object : DataCallback {
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
        MainActivityRepository.fetchCharacterMedia(realm, name, object : DataCallback {
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