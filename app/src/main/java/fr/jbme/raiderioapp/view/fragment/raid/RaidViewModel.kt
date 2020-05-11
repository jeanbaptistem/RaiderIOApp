package fr.jbme.raiderioapp.view.fragment.raid

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.service.model.blizzard.RaidInfo
import fr.jbme.raiderioapp.service.repository.RaidRepository
import fr.jbme.raiderioapp.service.repository.callback.DataCallback
import fr.jbme.raiderioapp.utils.network.Result
import fr.jbme.raiderioapp.view.activity.main.popupWindow.PopupCharacterItem
import java.util.*

@Suppress("UNCHECKED_CAST")
class RaidViewModel : ViewModel() {

    private val _viewSelectedCharacter = MutableLiveData<PopupCharacterItem>()
    private val _trueSelectedCharacter =
        Transformations.distinctUntilChanged(_viewSelectedCharacter)

    fun selectedCharacter(selectedCharName: PopupCharacterItem) {
        _viewSelectedCharacter.postValue(selectedCharName)
    }

    val characterRaidInfoLoading = MutableLiveData<Boolean>()
    val characterRaidInfo: LiveData<List<RaidInfo.Expansion.Instance>> =
        Transformations.switchMap(_trueSelectedCharacter) { character ->
            loadCharacterRaidInfo(character.realmSlug, character.name.toLowerCase(Locale.ROOT))
        }

    private fun loadCharacterRaidInfo(
        realm: String,
        name: String
    ): LiveData<List<RaidInfo.Expansion.Instance>> {
        val characterRaidInfoResult = MutableLiveData<List<RaidInfo.Expansion.Instance>>()
        characterRaidInfoLoading.value = true
        RaidRepository.fetchRaidInfo(realm, name, object :
            DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                characterRaidInfoResult.value = result.data as List<RaidInfo.Expansion.Instance>
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