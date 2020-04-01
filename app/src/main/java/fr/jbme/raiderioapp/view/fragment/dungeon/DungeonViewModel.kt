package fr.jbme.raiderioapp.view.fragment.dungeon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import fr.jbme.raiderioapp.service.model.blizzard.DungeonInfo
import fr.jbme.raiderioapp.service.model.raiderio.CharacterRanks
import fr.jbme.raiderioapp.service.repository.DungeonRepository
import fr.jbme.raiderioapp.service.repository.callback.DataCallback
import fr.jbme.raiderioapp.utils.Whatever
import fr.jbme.raiderioapp.utils.network.Result
import fr.jbme.raiderioapp.view.activity.main.popupWindow.PopupCharacterItem
import java.util.*

class DungeonViewModel : ViewModel() {
    private val _viewSelectedCharacter = MutableLiveData<PopupCharacterItem>()
    private val _trueSelectedCharacter =
        Transformations.distinctUntilChanged(_viewSelectedCharacter)

    fun selectedCharacter(selectedCharName: PopupCharacterItem?) {
        _viewSelectedCharacter.postValue(selectedCharName)
    }

    private val _characterRanksLoading = MutableLiveData<Boolean>()
    private val characterRanks: LiveData<CharacterRanks> =
        Transformations.switchMap(_trueSelectedCharacter) { character ->
            loadCharacterRanks(character.realmSlug, character.name.toLowerCase(Locale.ROOT))
        }

    private val _characterDungeonsLoading = MutableLiveData<Boolean>()
    private val characterDungeons: LiveData<List<DungeonInfo.BestRun>> =
        Transformations.switchMap(_trueSelectedCharacter) { character ->
            loadCharacterDungeons(character.realmSlug, character.name.toLowerCase(Locale.ROOT))
        }

    val zippedDungeonData: LiveData<Pair<CharacterRanks, List<DungeonInfo.BestRun>>> =
        Whatever.zipPair(characterRanks, characterDungeons)
    val zippedDungeonDataLoading: LiveData<Pair<Boolean, Boolean>> =
        Whatever.zipPair(_characterRanksLoading, _characterDungeonsLoading)


    private fun loadCharacterRanks(
        realm: String,
        name: String
    ): LiveData<CharacterRanks> {
        val characterRanksResult = MutableLiveData<CharacterRanks>()
        _characterRanksLoading.value = true
        DungeonRepository.fetchRanks(realm, name, object : DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                characterRanksResult.value = (result.data as CharacterRanks)
                _characterRanksLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                _characterRanksLoading.value = true
            }

        })
        return characterRanksResult
    }

    private fun loadCharacterDungeons(
        realm: String,
        name: String
    ): LiveData<List<DungeonInfo.BestRun>> {
        val characterDungeonsResult = MutableLiveData<List<DungeonInfo.BestRun>>()
        _characterDungeonsLoading.value = true
        DungeonRepository.fetchBlizzardDungeons(realm, name, object : DataCallback {
            override fun onDataLoaded(result: Result.Success<*>) {
                characterDungeonsResult.value =
                    (result.data as DungeonInfo).bestRuns as List<DungeonInfo.BestRun>
                _characterDungeonsLoading.value = false
            }

            override fun onDataNotAvailable(error: Result.Error) {
                _characterDungeonsLoading.value = true
            }

        })
        return characterDungeonsResult
    }
}