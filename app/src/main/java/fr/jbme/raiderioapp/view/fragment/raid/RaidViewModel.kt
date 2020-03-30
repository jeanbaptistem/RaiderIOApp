package fr.jbme.raiderioapp.view.fragment.raid

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.service.model.blizzard.raidInfo.Instances
import fr.jbme.raiderioapp.service.model.login.Result
import fr.jbme.raiderioapp.service.repository.RaidRepository
import fr.jbme.raiderioapp.service.repository.callback.DataCallback
import fr.jbme.raiderioapp.utils.Whatever

@Suppress("UNCHECKED_CAST")
class RaidViewModel : ViewModel() {

    private val _viewSelectedCharacter = MutableLiveData<String>()
    private val _trueSelectedCharacter =
        Transformations.distinctUntilChanged(_viewSelectedCharacter)

    fun selectedCharacter(selectedCharName: String?) {
        _viewSelectedCharacter.postValue(selectedCharName)
    }

    val characterRaidInfoLoading = MutableLiveData<Boolean>()
    val characterRaidInfo: LiveData<List<Instances>> =
        Transformations.switchMap(_trueSelectedCharacter) { character ->
            val name = Whatever.parseToSlug(character.split('-')[0])
            val realm = Whatever.parseToSlug(character.split('-')[1])
            loadCharacterRaidInfo(realm, name)
        }

    private fun loadCharacterRaidInfo(realm: String, name: String): LiveData<List<Instances>> {
        val characterRaidInfoResult = MutableLiveData<List<Instances>>()
        characterRaidInfoLoading.value = true
        RaidRepository.fetchRaidInfo(realm, name, object :
            DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                characterRaidInfoResult.value = result.data as List<Instances>
                characterRaidInfoLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                Log.i(
                    "Character armory  error",
                    error.exception.message.toString()
                )
                characterRaidInfoLoading.value = true
            }
        })
        return characterRaidInfoResult
    }
}